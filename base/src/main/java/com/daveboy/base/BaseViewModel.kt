package com.daveboy.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel:ViewModel(){
     fun <T>runOnUi(block:suspend ()->T,success:(T)->Unit,fail:(e:Throwable)->Unit ){
         viewModelScope.launch {
             runCatching {
                 block.invoke()
             }.onSuccess{
                 success.invoke(it)
             }.onFailure {
                 fail.invoke(it)
             }
         }
     }
}