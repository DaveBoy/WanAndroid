package com.daveboy.wanandroid.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter

@Entity
data class ArticleResponse{
    constructor(
        @Id
        var dbId:Long=0,
        val curPage: Int,
        @Convert(converter =ArticleConvert::class,dbType = String::class )
        val datas: List<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )
    constructor(){

    }
}
class ArticleConvert: PropertyConverter<List<Article>,String>{
    override fun convertToDatabaseValue(entityProperty: List<Article>?): String {
        return Gson().toJson(entityProperty)
    }

    override fun convertToEntityProperty(databaseValue: String?): List<Article> {
        return Gson().fromJson(databaseValue, object : TypeToken<List<Article>>() {
        }.type)
    }

}
open class Article(
    @Id
    var dbId:Long=0,
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    val top:Boolean=false
)

data class Tag(
    @Id
    var dbId:Long=0,
    val name: String,
    val url: String
)