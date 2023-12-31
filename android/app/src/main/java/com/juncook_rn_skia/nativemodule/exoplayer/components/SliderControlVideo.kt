package com.juncook_rn_skia.nativemodule.exoplayer.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_dark_onError
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_light_onPrimary
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_light_onSurfaceVariant
import com.juncook_rn_skia.nativemodule.utils.theme.md_theme_light_secondary
import java.util.concurrent.TimeUnit

@Composable
fun SliderControlVideo(
        modifier: Modifier = Modifier,
        totalDuration: Long,
        currentTime: Long,
        bufferPercentage: () -> Int,
        onSeekChanged: (timeMs: Float) -> Unit
) {
    val duration = remember(totalDuration) { totalDuration }

    val videoTime = remember(currentTime) { currentTime }

    val buffer = remember(bufferPercentage()) { bufferPercentage() }


    Column(modifier = modifier.height(50.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // buffer bar
            Slider(
                    value = buffer.toFloat(),
                    enabled = false,
                    onValueChange = { /*do nothing*/ },
                    valueRange = 0f..100f,
                    colors =
                    SliderDefaults.colors(
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTrackColor = md_theme_light_onSurfaceVariant
                    )
            )
            if (totalDuration > 0) {
                // seek bar
                Slider(
                        modifier = Modifier.fillMaxWidth(),
                        value = videoTime.toFloat(),
                        onValueChange = onSeekChanged,
                        valueRange = 0f..duration.toFloat(),
                        colors =
                        SliderDefaults.colors(
                                thumbColor = md_theme_light_onPrimary,
                                activeTickColor = md_theme_light_onPrimary,
                                activeTrackColor = md_theme_light_onPrimary,
                        )
                )
            }
        }


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