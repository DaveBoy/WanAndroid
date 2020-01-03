package com.daveboy.wanandroid

import android.app.Application
import com.daveboy.wanandroid.di.diModules
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
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
            fragmentFactory()
            this.modules(diModules)
        }
    }

    /**
     * 初始化工具类
     */
    private fun initUtil() {
        LiveEventBus
            .config()
            .lifecycleObserverAlwaysActive(false)
            .autoClear(true)
    }

    companion object{
        var instance:Application by Delegates.notNull()
    }
}