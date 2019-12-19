package com.daveboy.wanandroid.entity



data class TopArticleResponse(
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
    val zan: Int

){
    fun toArticle():Article{
        return Article( apkLink, author, chapterId, chapterName, collect, courseId, desc, envelopePic, fresh, id, link, niceDate, origin, prefix, projectLink, publishTime, superChapterId, superChapterName, tags, title, type, userId, visible, zan,top = true)
    }
}

