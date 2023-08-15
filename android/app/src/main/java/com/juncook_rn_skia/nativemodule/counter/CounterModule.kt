package com.juncook_rn_skia.nativemodule.counter

import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CounterModule(reactContext:ReactApplicationContext):ReactContextBaseJavaModule(reactContext) {

     private var count = 0
     override fun getName() = "Counter"

     @ReactMethod
     fun increment(callback: Callback){
         count += 1
         callback(count)
     }

    @ReactMethod
    fun decrement(promise: Promise){
      if(count == 0) {
         promise.reject("Errror")
      } else {
          count -= 1
          promise.resolve(count)
      }
    }

 }