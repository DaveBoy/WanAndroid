package com.daveboy.wanandroid.ui.system.navigation

import com.daveboy.base.BaseResponse
import com.daveboy.wanandroid.entity.NavigationModel
import com.daveboy.wanandroid.http.RetrofitManager

class NavigationRepository {



    suspend fun getNavigationList(): BaseResponse<List<NavigationModel>> {
        return RetrofitManager.service.getNavigationList()
    }


}