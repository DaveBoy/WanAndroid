package com.daveboy.base.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * 获取vm clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}


/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param viewState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmActivity<*>.parseState(
    viewState: ViewState<T>,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    when (viewState) {
        is ViewState.Loading -> {
            showProgress()
            onLoading?.run { this }
        }
        is ViewState.Success -> {
            dismissProgress()
            onSuccess(viewState.data)
        }
        is ViewState.Error -> {
            dismissProgress()
            onError?.run { this(viewState.error) }
        }
    }
}

fun <T> BaseVmFragment<*>.parseState(
    viewState: ViewState<T>,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    (activity as? BaseVmActivity<*>)?.parseState(viewState, onSuccess, onError, onLoading)
}

/**
 *我想了一东的时间，错误提示需要改一下
 */
fun Throwable?.parseErrorString(): String {
    return when (this) {
        is ConnectException -> "网络错误"
        is UnknownHostException -> "无网络连接"
        else ->"其他错误"
    }
}

/**
 * net request
 * @param request request method
 * @param viewState request result
 * @param showLoading 配置是否显示等待框
 */
fun <T> BaseViewModel.launchRequest(
    request: suspend () -> BaseEntity<T>,
    viewState: MutableLiveData<ViewState<T>>,
    showLoading: Boolean = true
) {
    viewModelScope.launch {
        runCatching {
            if (showLoading) viewState.value = ViewState.onAppLoading()
            request()
        }.onSuccess {
            viewState.paresResult(it)
        }.onFailure {
            viewState.paresException(it)
        }
    }
}