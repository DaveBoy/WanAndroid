package com.daveboy.wanandroid.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class UserModel(
    @Id
    var dbId:Long=0,
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