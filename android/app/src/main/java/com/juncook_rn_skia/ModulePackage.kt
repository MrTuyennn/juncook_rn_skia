package com.juncook_rn_skia

import androidx.media3.common.util.UnstableApi
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import com.juncook_rn_skia.nativemodule.counter.CounterModule
import com.juncook_rn_skia.nativemodule.exoplayer.ExoPlayerManager
@UnstableApi
class ModulePackage: ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> = listOf(
        CounterModule(reactContext)
    ).toMutableList()

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return listOf(ExoPlayerManager())
    }
}