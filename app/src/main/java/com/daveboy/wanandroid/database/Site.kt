package com.daveboy.wanandroid.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
@Entity
data class Site(
    @Id
    var dbId:Long=0,
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)