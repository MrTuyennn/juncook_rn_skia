package com.juncook_rn_skia.nativemodule.exoplayer

import com.facebook.react.bridge.ReactMethod
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class ExoPlayerManager: SimpleViewManager<ModViewContainer>() {

    override fun getName(): String {
        return "ExoPlayer"
    }

    @Override
    override fun createViewInstance(context: ThemedReactContext): ModViewContainer {
       return ModViewContainer(context)
    }

}