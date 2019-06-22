package com.daveboy.wanandroid.database

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.daveboy.common.BuildConfig
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

object BoxManager {
    lateinit var boxStore: BoxStore
        private set
    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()

        if (BuildConfig.DEBUG) {
            LogUtils.i("Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
            AndroidObjectBrowser(boxStore).start(context)
        }
    }
}