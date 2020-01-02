package com.daveboy.wanandroid.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.entity.*
import kotlinx.coroutines.launch

class SearchKeyViewModel: BaseViewModel() {
    val keyWord=MutableLiveData<String>()
    val showDetailPage=MutableLiveData<Boolean>()
}