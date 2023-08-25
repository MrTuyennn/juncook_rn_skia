 package com.juncook_rn_skia.nativemodule.exoplayer


import android.widget.FrameLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
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
fun ModView(link:String?) {

    val contextLocal = LocalContext.current
    // create our player
    val exoPlayer = remember {
        ExoPlayer.Builder(contextLocal).apply {
            setSeekBackIncrementMs(PLAYER_SEEK_BACK_INCREMENT)
            setSeekForwardIncrementMs(PLAYER_SEEK_FORWARD_INCREMENT)  }
            .build().apply {
            setMediaItem(
                MediaItem.fromUri("$link")
            )
            prepare()
            playWhenReady = true

        }
    }

    val size = Size()
    val widthScreen = size.width()


    var shouldShowControls by remember { mutableStateOf(false) }

    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }

    var totalDuration by remember { mutableStateOf(0L) }

    var currentTime by remember { mutableStateOf(0L) }

    var bufferedPercentage by remember { mutableStateOf(0) }

    var playbackState by remember { mutableStateOf(exoPlayer.playbackState) }

    Column {
        Box(modifier = Modifier
            .height((widthScreen * 9 / 16).dp)
            .width(widthScreen.dp)) {
            DisposableEffect(exoPlayer) { val listener =
                            object : Player.Listener {
                                override fun onEvents(player: Player, events: Player.Events) {
                                    super.onEvents(player, events)
                                    if (
                            events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED) ||
                            events.contains(Player.EVENT_PLAY_WHEN_READY_CHANGED)
                        ) {
                            Log.i("player.currentPosition","${player.currentPosition}")
                                        totalDuration = player.duration
                                        currentTime = player.currentPosition
                                        bufferedPercentage = player.bufferedPercentage
                                        isPlaying = player.isPlaying
                                        playbackState = player.playbackState
                        }

                    }
                }

                exoPlayer.addListener(listener)

                onDispose {
                    exoPlayer.removeListener(listener)
                    exoPlayer.release()
                } }
            if (isPlaying) {
                LaunchedEffect(Unit) {
                    while(true) {
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
                                ViewGroup.LayoutParams.FILL_PARENT
                            )
                        useController = false
                    }
                }
            )

        }
        SliderControlVideo(
            totalDuration = { totalDuration },
            currentTime = { currentTime },
            bufferPercentage = { bufferedPercentage },
            onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) }
        )
       //bottom control isPlay isPause
       BoxControlVideo(
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
           isPlaying = {isPlaying}
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



