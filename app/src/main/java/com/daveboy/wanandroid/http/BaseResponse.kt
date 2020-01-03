package com.daveboy.wanandroid.http

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.daveboy.base.IResponse
import com.daveboy.wanandroid.ui.login.LoginActivity

data class BaseResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T):IResponse<T>{
    override fun isSuccess():Boolean{
        return errorCode==0
    }
    override fun getErrorInfo():String{
        return errorMsg
    }

    /**
     * 是否消费异常
     * @return Boolean
     */
    override fun atErrorCode():Boolean{
        return if(errorCode==-1001) {//未登录
            ActivityUtils.getTopActivity()?.let {
                it.startActivity(Intent(it,LoginActivity::class.java))
            }
            true
        }else false
    }

    override fun getRealData(): T {
        return data
    }
}
