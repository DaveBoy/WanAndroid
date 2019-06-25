package com.daveboy.wanandroid.ui.main.index

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.database.Article

class IndexAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article) {

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.item_author, item.author)
            .setText(R.id.item_tag, item.superChapterName.plus("/").plus(item.chapterName))
            .setText(R.id.item_title,
                if(item.title.contains("<em class='highlight'>"))
                    Html.fromHtml("<html> ${item.title.replace("<em class='highlight'>","<font color=\"#ff0000\">").replace("</em>","</font>")}")//<em class='highlight'>
                else
                    item.title)
            .setText(R.id.item_time, item.niceDate)
            .setGone(R.id.item_top, item.top)
            .setGone(R.id.item_new, item.fresh)
            .setGone(R.id.item_tag1, item.tags.isNullOrEmpty().not())
            .setText(R.id.item_tag1, item.tags.joinToString {
                it.name
            })
            .setImageResource(
                R.id.item_like,
                if (item.collect) R.mipmap.timeline_like_pressed else R.mipmap.timeline_like_normal
            )
    }
}