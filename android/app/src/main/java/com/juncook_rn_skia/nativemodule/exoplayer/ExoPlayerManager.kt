package com.juncook_rn_skia.nativemodule.exoplayer

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.ui.platform.ComposeView
import androidx.media3.common.util.UnstableApi
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

@UnstableApi
class ExoPlayerManager(reactContext: ReactApplicationContext) : SimpleViewManager<LinearLayout>() {

    private val context = reactContext

    override fun getName(): String {
        return "ExoPlayer"
    }

    @Override
    override fun createViewInstance(context: ThemedReactContext): LinearLayout {
        val layout = LinearLayout(context)
        layout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val composeView = ComposeView(context)
        layout.addView(composeView)
        return layout
    }

    @ReactProp(name = "linkVideo")
    fun setText(layout: LinearLayout, linkVideo: String?) {
        val composeView = layout.getChildAt(0) as ComposeView
        composeView.setContent {
            ExoPlayerView(link = linkVideo, context = context)
        }
    }

    override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> {
        return mapOf(
            "topChange" to mapOf(
                "phasedRegistrationNames" to mapOf(
                    "bubbled" to "onChange"
                )
            )
        )
    }


}