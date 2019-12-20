package com.daveboy.common.util

import android.app.Activity
import com.blankj.utilcode.util.ToastUtils

fun toast(res:Int?=null,text:String?=null){
    res?.let {
        ToastUtils.showShort(it)
    }
    text?.let {
        ToastUtils.showShort(it)
    }
}