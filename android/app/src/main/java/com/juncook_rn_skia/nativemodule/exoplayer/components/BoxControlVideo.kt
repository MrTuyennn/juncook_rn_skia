package com.juncook_rn_skia.nativemodule.exoplayer.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.R
import com.juncook_rn_skia.nativemodule.utils.Size

@Composable
fun BoxControlVideo(
    onClickPlay : () -> Unit = {},
    onClickPrev : () -> Unit = {},
    onClickNext: () -> Unit = {},
    onClickNextVideo:() -> Unit,
    onClickPrevVideo:() -> Unit,
    isPlaying: () -> Boolean
) {
    val size = Size()
    val widthScreen = size.width()
    val isVideoPlaying = remember(isPlaying()) { isPlaying() }

    Row(modifier = Modifier
        .width(widthScreen.dp)
        .height(50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        BoxComponent {
            LogoComponent(image = R.drawable.lb_ic_skip_previous, size = 45.dp, onClick = onClickPrevVideo)
        }
        BoxComponent {
            LogoComponent(image = R.drawable.quantum_ic_replay_10_white_24, size = 45.dp, onClick = { onClickPrev()})
        }
        BoxComponent {
            if(!isVideoPlaying){
                LogoComponent(image =  R.drawable.lb_ic_play, size = 45.dp, onClick = {onClickPlay()})
            } else {
                LogoComponent(image =  R.drawable.lb_ic_pause, size = 45.dp, onClick = {onClickPlay()})
            }
        }
        BoxComponent {
            LogoComponent(image = R.drawable.quantum_ic_forward_10_white_24, size = 45.dp, onClick = { onClickNext()})
        }
        BoxComponent {
            LogoComponent(image = R.drawable.lb_ic_skip_next, size = 45.dp, onClick = onClickNextVideo)
        }
    }
}

@Composable
fun BoxComponent(
    modifier: Modifier = Modifier,
    content : @Composable () -> Unit
) {
    Box(modifier = modifier.padding(10.dp)){
        content()
    }
}