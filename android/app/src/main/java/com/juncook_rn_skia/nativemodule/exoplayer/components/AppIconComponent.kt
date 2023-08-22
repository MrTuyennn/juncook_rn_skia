package com.juncook_rn_skia.nativemodule.exoplayer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_light_onPrimary

@Composable
fun AppIconComponent(
    @DrawableRes icon : Int,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    background: Color = md_theme_light_onPrimary,
    onClick : () -> Unit = {}
) {
   Box(modifier = modifier
       .background(background, CircleShape)
       .size(46.dp)
       .clickable { onClick() }
       ,contentAlignment = Alignment.Center
   ){
       IconComponent(icon = icon,tint = tint)
   }
}

@Composable
fun IconComponent(
    @DrawableRes icon: Int,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
    size : Dp = 20.dp
) {
    Icon(painter = painterResource(id = icon),
         contentDescription = null,
         modifier = modifier.size(size),
         tint = tint)
}