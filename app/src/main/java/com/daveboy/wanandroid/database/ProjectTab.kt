package com.daveboy.wanandroid.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ProjectTab(
    @Id
    var dbId:Long=0,
    //val children: List<String>,//应该是备用字段  暂时都是空
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)