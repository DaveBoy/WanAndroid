package com.daveboy.wanandroid.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter

@Entity
data class TopArticleResponse(

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
    @Convert(converter =TagConvert::class,dbType = String::class )
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int

){
    fun toArticle():Article{
        return Article(dbId, apkLink, author, chapterId, chapterName, collect, courseId, desc, envelopePic, fresh, id, link, niceDate, origin, prefix, projectLink, publishTime, superChapterId, superChapterName, tags, title, type, userId, visible, zan,top = true)
    }
}
class TagConvert: PropertyConverter<List<Tag>, String> {
    override fun convertToDatabaseValue(entityProperty: List<Tag>?): String {
        return Gson().toJson(entityProperty)
    }

    override fun convertToEntityProperty(databaseValue: String?): List<Tag> {
        return Gson().fromJson(databaseValue, object : TypeToken<List<Tag>>() {
        }.type)
    }

}
