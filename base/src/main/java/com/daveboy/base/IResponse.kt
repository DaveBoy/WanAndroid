package com.daveboy.base

interface IResponse<out T>{
    fun getRealData():T
    fun isSuccess():Boolean
    fun getErrorInfo():String
    fun atErrorCode():Boolean
}
