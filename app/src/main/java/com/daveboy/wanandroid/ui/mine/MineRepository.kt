package com.daveboy.wanandroid.ui.mine

import com.daveboy.wanandroid.http.BaseResponse
import com.daveboy.wanandroid.entity.Score
import com.daveboy.wanandroid.http.RetrofitManager

class MineRepository {
    suspend fun getScore(): BaseResponse<Score> {
        return RetrofitManager.service.getScore()
    }
}