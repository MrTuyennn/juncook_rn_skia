package com.juncook_rn_skia.nativemodule.utils

import android.content.Context


fun GetApplicationName(context:Context): String {
    val packageName = context.packageName
    val packageManger = context.packageManager
    val applicationInfo = packageManger.getApplicationInfo(packageName,0)
    return packageManger.getApplicationLabel(applicationInfo).toString()
}
