package com.daveboy.wanandroid

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.daveboy.wanandroid.database.BoxManager
import me.yokeyword.fragmentation.Fragmentation
import me.yokeyword.fragmentation.helper.ExceptionHandler
import kotlin.properties.Delegates

class CustomApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
        initUtil()
        initDataBase()
        initFragment()
    }

    private fun initFragment() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                .handleException(object : ExceptionHandler {
                    override fun onException(e: Exception) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install()
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