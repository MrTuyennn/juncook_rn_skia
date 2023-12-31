package com.juncook_rn_skia.nativemodule.exoplayer


import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.juncook_rn_skia.nativemodule.exoplayer.components.BoxControlVideo
import com.juncook_rn_skia.nativemodule.exoplayer.components.SliderControlVideo
import com.juncook_rn_skia.nativemodule.utils.Size
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds


private const val PLAYER_SEEK_BACK_INCREMENT = 5 * 1000L // 5 seconds
private const val PLAYER_SEEK_FORWARD_INCREMENT = 5 * 1000L // 10 seconds

@UnstableApi
@Composable
fun ExoPlayerView(link: String?,onNextVideo:() -> Unit,onPrevVideo:() -> Unit) {

    val contextLocal = LocalContext.current

    val size = Size()
    val widthScreen = size.width()

    var isPlaying by remember { mutableStateOf(false) }

    var totalDuration by remember { mutableStateOf(0L) }

    var currentTime by remember { mutableStateOf(0L) }

    var bufferedPercentage by remember { mutableStateOf(0) }

    var playbackState by remember { mutableStateOf(0) }

    // create our player
    val exoPlayer = remember {
        ExoPlayer.Builder(contextLocal).apply {
            setSeekBackIncrementMs(PLAYER_SEEK_BACK_INCREMENT)
            setSeekForwardIncrementMs(PLAYER_SEEK_FORWARD_INCREMENT)
        }
            .build().apply {
                setMediaItem(
                    MediaItem.fromUri("$link")
                )
                addListener(object : Player.Listener {
                    override fun onEvents(player: Player, events: Player.Events) {
                        super.onEvents(player, events)
                        if (
                            events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED) ||
                            events.contains(Player.EVENT_PLAY_WHEN_READY_CHANGED)
                        ) {
                            Log.i("player.currentPosition", "${player.currentPosition}")
                            totalDuration = player.duration
                            currentTime = player.currentPosition
                            bufferedPercentage = player.bufferedPercentage
                            isPlaying = player.isPlaying
                            playbackState = player.playbackState
                        }

                    }
                })
                prepare()
                playWhenReady = true

            }
    }

    DisposableEffect(link) {
        exoPlayer.setMediaItem(MediaItem.fromUri("$link"))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        onDispose {
            exoPlayer.stop()
        }
    }



    Column {
        Box(
            modifier = Modifier
                .height((widthScreen * 9 / 16).dp)
                .width(widthScreen.dp)
        ) {
            if (isPlaying) {
                LaunchedEffect(Unit) {
                    while (true) {
                        currentTime = exoPlayer.currentPosition
                        delay(1.seconds / 30)
                    }
                }
            }
            AndroidView(
                factory = {
                    PlayerView(contextLocal).apply {
                        player = exoPlayer
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,

                            )
                        useController = false
                    }
                }

            )

        }
        SliderControlVideo(
            totalDuration = totalDuration,
            currentTime = currentTime,
            bufferPercentage = { bufferedPercentage },
            onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) }
        )
        //bottom control isPlay isPause
        BoxControlVideo(
            onClickPrevVideo = onPrevVideo,
            onClickNextVideo = onNextVideo,
            onClickNext = {
                exoPlayer.seekForward()
            },
            onClickPlay = {
                when {
                    exoPlayer.isPlaying -> {
                        // pause the video
                        exoPlayer.pause()
                    }

                    exoPlayer.isPlaying.not() &&
                            playbackState == Player.STATE_ENDED -> {
                        exoPlayer.seekTo(0)
                        exoPlayer.playWhenReady = true
                    }

                    else -> {
                        // play the video
                        // it's already paused
                        exoPlayer.play()
                    }
                }
                isPlaying = isPlaying.not()
            },
            onClickPrev = {
                exoPlayer.seekBack()
            },
            isPlaying = { isPlaying }
        )
        //bottom control isPlay isPause
    }
}

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    }
}



