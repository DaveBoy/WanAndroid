package com.daveboy.wanandroid

import android.app.Application
import okhttp3.internal.Internal.instance
import kotlin.properties.Delegates

class CustomApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
        initUtil()
    }

    /**
     * 初始化工具类
     */
    private fun initUtil() {

    }

    companion object{
        var instance:Application by Delegates.notNull()
    }
}