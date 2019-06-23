package com.daveboy.wanandroid.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class BannerResponse(
    @Id
    var dbId:Long=0,
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)