package com.daveboy.wanandroid.ui.system

import com.daveboy.wanandroid.entity.SystemModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse

class SystemRepository {



    suspend fun getSystemList(): WanResponse<List<SystemModel>> {
        return RetrofitManager.service.getSystemList()
    }


}