package com.juncook_rn_skia.nativemodule.exoplayer

import android.widget.FrameLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.nativemodule.utils.Size


@Composable
fun ModView(link:String?) {

    val contextLocal = LocalContext.current

    val size = Size()
    val widthScreen = size.width()

    var isPlaying by remember { mutableStateOf(true) }

    // create our player
    val exoPlayer = remember {
            ExoPlayer.Builder(contextLocal).build().apply {
                setMediaItem(
                    MediaItem.fromUri("$link")
                )
                prepare()
                playWhenReady = true

            }
    }
    Column {
        Column(modifier = Modifier
            .height((widthScreen * 9 / 16).dp)
            .width(widthScreen.dp)) {
            DisposableEffect(key1 = Unit) { onDispose { exoPlayer.release() } }
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
       BoxControlVideo(
           onClickNext = {
               exoPlayer.seekForward()
           },
           onClickPlay = {
           if (exoPlayer.isPlaying) {
               // pause the video
               exoPlayer.pause()
           } else {
               // play the video
               // it's already paused
               exoPlayer.play()
           }
           isPlaying = isPlaying.not()
           },
           onClickPrev = {
               exoPlayer.seekBack()
           },
           isPlaying = {isPlaying})
    }
}


