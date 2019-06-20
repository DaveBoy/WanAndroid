package com.daveboy.common.Util

import com.blankj.utilcode.util.SPUtils

fun getString(key:String,defaultValue:String?=null):String?{
    return SPUtils.getInstance().getString(key,defaultValue)
}


fun put(key:String,value:String){
    SPUtils.getInstance().put(key,value)
}