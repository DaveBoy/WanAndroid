package com.daveboy.wanandroid.ui.main.index

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.common.util.fromHtml
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.Article

class ArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article) {

    override fun convert(helper: BaseViewHolder, item: Article) {
        val name=(item.author+(item.shareUser?:"")).let {
            if(it.isEmpty()) "匿名" else it
        }
        helper.setText(R.id.item_author,name )
            .setText(R.id.item_tag, (item.superChapterName?.plus("/")?:"").plus(item.chapterName))
            .setText(R.id.item_title,
                    item.title.fromHtml())
            .setText(R.id.item_time, item.niceDate)
            .setGone(R.id.item_top, item.top)
            .setGone(R.id.item_new, item.fresh)
            .setGone(R.id.item_tag1, item.tags.isNullOrEmpty().not())
            .setText(R.id.item_tag1, item.tags?.joinToString {
                it.name
            }?:"")
            .setImageResource(
                R.id.item_like,
                if (item.collect) R.mipmap.timeline_like_pressed else R.mipmap.timeline_like_normal
            )
            .addOnClickListener(R.id.item_like)
    }
}