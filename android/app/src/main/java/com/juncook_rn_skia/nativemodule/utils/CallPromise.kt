package com.juncook_rn_skia.nativemodule.utils

class CallPromise<T> {
    private var successCallback: ((T) -> Unit)? = null
    private var errorCallback: ((Throwable) -> Unit)? = null

    fun resolve(value: T) {
        successCallback?.invoke(value)
    }

    fun reject(error: Throwable) {
        errorCallback?.invoke(error)
    }

    fun then(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): CallPromise<T> {
        successCallback = onSuccess
        errorCallback = onError
        return this
    }
}