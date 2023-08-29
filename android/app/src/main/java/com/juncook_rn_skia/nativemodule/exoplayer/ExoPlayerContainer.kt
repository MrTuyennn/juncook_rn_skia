package com.juncook_rn_skia.nativemodule.exoplayer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.util.UnstableApi

@UnstableApi
@Composable
fun ExoPlayerContainer(link:String?) {
    Log.i("Link ===>","${link}")
    Column(modifier = Modifier.fillMaxHeight()) {

        ListVideoView()
    }
}
