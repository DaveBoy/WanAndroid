package com.daveboy.base

data class BaseResponse<out T>(private val errorCode: Int, val errorMsg: String, val data: T){
    fun isSuccess():Boolean{
        return errorCode==1
    }
    fun getErrorInfo():String{
        return errorMsg
    }
}
