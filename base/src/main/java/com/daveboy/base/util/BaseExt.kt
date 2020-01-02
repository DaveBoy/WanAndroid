package com.daveboy.base.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daveboy.base.*
import com.daveboy.base.core.RequestException
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
fun <T> BaseActivity.parseState(
    viewState: ViewState<T>,
    onSuccess: (T) -> Unit,
    onError: ((RequestException) -> Unit)? = null,
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
            onError?.run { this(viewState.exception) }
        }
    }
}

fun <T> BaseVMFragment<*>.parseState(
    viewState: ViewState<T>,
    onSuccess: (T) -> Unit,
    onError: ((RequestException) -> Unit)? = null,
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
            onError?.run { this(viewState.exception) }
        }
    }
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
 * 处理返回值
 *
 * @param result 请求结果
 */
fun <T> MutableLiveData<ViewState<T>>.paresResult(result: BaseResponse<T>) {
    value = if (result.isSuccess()) ViewState.onSuccess(result.data) else
        ViewState.onError(RequestException(result.getErrorInfo()))
}

/**
 * 异常转换异常处理
 */
fun <T> MutableLiveData<ViewState<T>>.paresException(e: Throwable) {
    this.value = ViewState.onError(RequestException(e))
}
/**
 * net request
 * @param request request method
 * @param viewState request result
 * @param showLoading 配置是否显示等待框
 */
fun <T> BaseViewModel.launchRequest(
    request: suspend () -> BaseResponse<T>,
    viewState: MutableLiveData<ViewState<T>>,
    showLoading: Boolean = true,
    success:(BaseResponse<T>)->Unit={},
    error:(Throwable)->Unit={}
) {
    viewModelScope.launch {
        runCatching {
            if (showLoading) viewState.value = ViewState.onLoading()
            request()
        }.onSuccess {
            success.invoke(it)
            viewState.paresResult(it)
        }.onFailure {
            error.invoke(it)
            viewState.paresException(it)
        }
    }
}