package com.juncook_rn_skia.nativemodule.exoplayer


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.nativemodule.utils.Size

@Composable
fun ListVideoView() {

    val size = Size()
    val heightScreen = size.height()

    Column(modifier = Modifier
        .height((heightScreen / 2).dp)
        .fillMaxWidth().background(Color.White)) {
        Text(text = "Android", color = Color.Red)
        Text(text = "ngoc Duyen")
        Text(text = "ngoc Duyen")
        Text(text = "ngoc Duyen")
    }
}

@Preview
@Composable
fun ListVideo(){
    ListVideoView()
}