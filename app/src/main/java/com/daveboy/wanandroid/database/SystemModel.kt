package com.daveboy.wanandroid.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter

@Entity
data class SystemModel(
    @Id
    var dbId:Long=0,
    @Convert(converter =SystemModelConvert::class,dbType = String::class )
    val children: List<SystemTopic>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
class SystemModelConvert: PropertyConverter<List<SystemModel>, String> {
    override fun convertToDatabaseValue(entityProperty: List<SystemModel>?): String {
        return Gson().toJson(entityProperty)
    }

    override fun convertToEntityProperty(databaseValue: String?): List<SystemModel> {
        return Gson().fromJson(databaseValue, object : TypeToken<List<SystemModel>>() {
        }.type)
    }

}
data class SystemTopic(
    //val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)