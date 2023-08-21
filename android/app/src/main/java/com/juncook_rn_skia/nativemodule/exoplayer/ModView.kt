package com.juncook_rn_skia.nativemodule.exoplayer

import android.net.Uri
import android.util.Log
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


@Composable
fun ModView(link:String?) {


    val contextLocal = LocalContext.current
    val contentUri: Uri = Uri.parse("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")


    // create our player
    val exoPlayer = remember {

            ExoPlayer.Builder(contextLocal).build().apply {
                setMediaItem(
                    MediaItem.fromUri("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
                )
                prepare()
                playWhenReady = true
            }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier) {
            DisposableEffect(key1 = Unit) { onDispose { exoPlayer.release() } }
            AndroidView(
                factory = {
                    PlayerView(contextLocal).apply {
                        player = exoPlayer
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                    }
                }
            )

        }
    }
}

//@Composable
//fun MediaSource(uri : String?,overrideExtension: String?) {
//    val type: Int? = uri?.let { Util.inferContentType(it) }
//    return when (type) {
//        C.TYPE_DASH -> DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//        C.TYPE_SS -> SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//        C.TYPE_HLS -> HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//        C.TYPE_OTHER -> ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//        else -> throw IllegalStateException("Unsupported type: $type")
//    }
//}