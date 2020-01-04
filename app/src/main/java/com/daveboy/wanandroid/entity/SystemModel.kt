package com.daveboy.wanandroid.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SystemModel(
    val children: List<SystemTopic>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable
@Parcelize
data class SystemTopic(
    //val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable