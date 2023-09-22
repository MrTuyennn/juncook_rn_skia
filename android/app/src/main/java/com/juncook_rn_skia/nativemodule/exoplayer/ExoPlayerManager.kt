package com.juncook_rn_skia.nativemodule.exoplayer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.RCTEventEmitter

@UnstableApi
class ExoPlayerManager() : SimpleViewManager<ComposeView>() {

    private var context: ReactContext? = null
    private var linkVideo: String = ""

    override fun getName(): String {
        return "ExoPlayer"
    }


    @SuppressLint("SuspiciousIndentation")
    override fun createViewInstance(context: ThemedReactContext): ComposeView {
      this.context = context
        return ComposeView(context).apply {
               setContent {
                   fun onNextVideo(): Unit {
                       val reactContext = context as ReactContext
                       val event: WritableMap = Arguments.createMap()
                       reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(id, "onNextVideo", event)
                   }
                   fun onPrevVideo(): Unit {
                       val reactContext = context as ReactContext
                       val event: WritableMap = Arguments.createMap()
                       reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(id, "onPrevVideo", event)
                   }
                   ExoPlayerView(link = linkVideo, onNextVideo= { onNextVideo() },onPrevVideo={onPrevVideo()})
               }
        }
    }

    @ReactProp(name = "linkVideo")
    fun setLinkVideo(view: ComposeView, linkVideo: String?) {
        if (linkVideo != null) {
            Log.i("Manager Video",linkVideo)
            this.linkVideo = linkVideo
            view.setContent {
                fun onNextVideo(): Unit {
                    val reactContext = context as ReactContext
                    val event: WritableMap = Arguments.createMap()
                    reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(view.id, "onNextVideo", event)
                }
                fun onPrevVideo(): Unit {
                    val reactContext = context as ReactContext
                    val event: WritableMap = Arguments.createMap()
                    reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(view.id, "onPrevVideo", event)
                }
                ExoPlayerView(link = linkVideo, onNextVideo= { onNextVideo() },onPrevVideo={onPrevVideo()})
            }
        }
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any>? {
        return MapBuilder.builder<String, Any>()
            .put("onNextVideo",
                MapBuilder.of("registrationName", "onNextVideo"))
            .put("onPrevVideo",
                MapBuilder.of("registrationName", "onPrevVideo"))
            .build()
    }

}