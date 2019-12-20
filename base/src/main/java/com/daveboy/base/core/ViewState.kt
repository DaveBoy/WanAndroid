package com.daveboy.base.core

sealed class ViewState<out T> {
    companion object{
        fun loading()=Loading
        fun <T>success(data:T)=Success(data)
        fun error(data:T)=Success(data)
    }

    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data:T):ViewState<T>()
    data class Error<out T>(val exception: QuestException):ViewState<T>()

}