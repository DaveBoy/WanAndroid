package com.daveboy.wanandroid.entity


data class UserModel(
    val admin: Boolean,
   /* val chapterTops: List<Any>,
    val collectIds: List<Int>,*/
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)