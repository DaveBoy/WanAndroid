package com.daveboy.wanandroid.entity

data class ProjectTab(
    //val children: List<String>,//应该是备用字段  暂时都是空
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)