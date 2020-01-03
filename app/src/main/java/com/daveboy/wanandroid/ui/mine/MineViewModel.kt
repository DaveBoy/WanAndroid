package com.daveboy.wanandroid.ui.mine

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.Score
import org.koin.core.inject

class MineViewModel:BaseViewModel() {
    val score=MutableLiveData<ViewState<Score>>()
    val repository:MineRepository by inject()
    fun getScore(){
        launchRequest({repository.getScore()},score)
    }
}