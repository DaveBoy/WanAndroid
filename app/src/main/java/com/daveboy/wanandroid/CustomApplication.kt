package com.daveboy.wanandroid

import android.app.Application
import com.daveboy.wanandroid.di.diModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import kotlin.properties.Delegates

class CustomApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
        initUtil()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            this.androidContext(this@CustomApplication)
            this.modules(diModules)
        }
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