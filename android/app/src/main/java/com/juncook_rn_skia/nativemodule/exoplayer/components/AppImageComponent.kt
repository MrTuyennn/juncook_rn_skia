package com.juncook_rn_skia.nativemodule.exoplayer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LogoComponent(
    modifier: Modifier = Modifier,
    size: Dp = 155.dp,
    @DrawableRes image: Int,
    onClick : () -> Unit = {}
) {

    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = modifier.size(size = size).clickable { onClick() },
    )

}