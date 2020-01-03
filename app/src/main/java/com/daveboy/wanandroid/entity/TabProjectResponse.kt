package com.daveboy.wanandroid.entity

data class TabProjectResponse(
    val curPage: Int,
    val datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)


data class ProjectTag(
    val name: String,
    val url: String
)