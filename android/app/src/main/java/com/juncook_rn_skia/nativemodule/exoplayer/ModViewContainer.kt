package com.juncook_rn_skia.nativemodule.exoplayer

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView

class ModViewContainer(context: Context) : FrameLayout(context){
    private val rootView = findViewById<View>(android.R.id.content)

    init {
        // Inflate the Compose UI component and add it to the root view
        val composeView = ComposeView(context)
        composeView.setContent {
            ModView()
        }
        addView(composeView)
    }
}