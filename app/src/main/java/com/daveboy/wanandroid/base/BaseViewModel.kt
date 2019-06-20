package com.daveboy.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveboy.wanandroid.http.WanResponse
import com.daveboy.wanandroid.util.logi
import kotlinx.coroutines.*

open class BaseViewModel:ViewModel() {
    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch {
            block()
        }
    }
    protected suspend fun executeResponse(response: WanResponse<Any>,success:suspend CoroutineScope.()->Unit,failed:suspend CoroutineScope.()->Unit){
        coroutineScope {
            if(response.errorCode==-1) {
                logi("request error:${response.errorMsg}")
                failed()
            }
            else {
                logi("request success:${response.data}")
                success()
            }
        }

    }
}