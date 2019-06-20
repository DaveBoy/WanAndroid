package com.daveboy.wanandroid

import android.app.Application
import com.tencent.mmkv.MMKV
import kotlin.properties.Delegates

class CustomApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
        MMKV.initialize(this)
    }
    companion object{
        var instance:Application by Delegates.notNull()
    }
}