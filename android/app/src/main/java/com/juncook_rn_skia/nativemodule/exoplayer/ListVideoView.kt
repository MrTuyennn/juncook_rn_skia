package com.juncook_rn_skia.nativemodule.exoplayer


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.nativemodule.utils.Size
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_light_primary

@Composable
fun ListVideoView() {

    val size = Size()
    val heightScreen = size.height()

    Column(modifier = Modifier.height((heightScreen/2).dp).fillMaxWidth()) {
        Text(text = "Android", color = Color.White)
    }
}