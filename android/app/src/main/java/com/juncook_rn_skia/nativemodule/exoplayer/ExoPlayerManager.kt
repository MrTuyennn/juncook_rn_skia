package com.juncook_rn_skia.nativemodule.exoplayer

import androidx.compose.ui.platform.ComposeView
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp


class ExoPlayerManager: SimpleViewManager<ModViewContainer>() {

    override fun getName(): String {
        return "ExoPlayer"
    }

    @Override
    override fun createViewInstance(context: ThemedReactContext): ModViewContainer {
       return ModViewContainer(context)
    }

    @ReactProp(name = "style")
    fun setStyle(composeView: ModViewContainer?, style: ReadableMap?) {
        // Here you can handle style properties passed from React Native.
        // You might need to parse the style object and apply it to your ComposeView.
    }

}