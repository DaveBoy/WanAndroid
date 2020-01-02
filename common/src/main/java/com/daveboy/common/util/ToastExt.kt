package com.daveboy.common.util

import com.blankj.utilcode.util.ToastUtils

fun toast(res:Int?=null,text:String?=null){
    res?.let {
        ToastUtils.showShort(it)
    }
    text?.let {
        ToastUtils.showShort(it)
    }
}
fun String.toast()= toast(text=this)
fun Int.toast()= toast(res=this)