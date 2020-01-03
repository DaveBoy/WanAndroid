package com.daveboy.wanandroid.ui.project.content

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.common.util.fromHtml
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.Article

class ContentAdapter:BaseQuickAdapter<Article,BaseViewHolder>(R.layout.item_project) {
    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.project_title,item.title.fromHtml())
            .setText(R.id.project_des,item.desc)
            .setText(R.id.project_author,item.author)
            .setText(R.id.project_time,item.niceDate)
            .setImageResource(
                R.id.project_like,
                if (item.collect) R.mipmap.timeline_like_pressed else R.mipmap.timeline_like_normal
            )
            .addOnClickListener(R.id.project_like)
        Glide.with(mContext)
            .load(item.envelopePic)
            .into(helper.getView(R.id.project_img))

    }
}