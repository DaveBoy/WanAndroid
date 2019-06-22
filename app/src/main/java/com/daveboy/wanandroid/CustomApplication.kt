package com.daveboy.wanandroid

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.daveboy.wanandroid.database.BoxManager
import kotlin.properties.Delegates

class CustomApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
        initUtil()
        initDataBase()
    }

    private fun initDataBase() {
        BoxManager.init(this)
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