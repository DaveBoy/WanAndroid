package com.daveboy.base

data class BaseResponse<out T>(private val errorCode: Int, val errorMsg: String, val data: T){
    fun isSuccess():Boolean{
        return errorCode==0
    }
    fun getErrorInfo():String{
        return errorMsg
    }
}
