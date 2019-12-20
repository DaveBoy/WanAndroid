package com.daveboy.base.core

sealed class ViewState<out T> {
    companion object{
        fun onLoading()=Loading
        fun <T>onSuccess(data:T)=Success(data)
        fun onError(e:RequestException)=Error(e)
    }

    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data:T):ViewState<T>()
    data class Error(val exception: RequestException):ViewState<Nothing>()

}