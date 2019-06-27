package com.daveboy.wanandroid.ui.system

import com.daveboy.wanandroid.database.BoxManager
import com.daveboy.wanandroid.database.SystemModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class SystemRepository {

    private lateinit var systemBox : Box<SystemModel>


    suspend fun getSystemList(): WanResponse<List<SystemModel>> {
        return RetrofitManager.service.getSystemList()
    }

    fun insertSystem(data: List<SystemModel>) {
        if(!::systemBox.isInitialized) {
            systemBox = BoxManager.boxStore.boxFor()
        }
        systemBox.removeAll()
        systemBox.put(data)
    }
}