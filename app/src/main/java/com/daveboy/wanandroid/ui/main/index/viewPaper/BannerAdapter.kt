package com.daveboy.wanandroid.ui.main.index.viewPaper

import android.media.Image
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.BannerResponse


class BannerAdapter :BaseQuickAdapter<BannerResponse,BaseViewHolder>(R.layout.item_banner){

    fun setData(list:MutableList<BannerResponse>){
        if(list.isNullOrEmpty())
            return
        data.clear()
        data.add(list[list.size-1])
        data.addAll(list)
        data.add(list[0])
        notifyDataSetChanged()
    }
    override fun convert(helper: BaseViewHolder, item: BannerResponse) {
        Glide
            .with(mContext)
            .load(item.imagePath)
            .into(helper.getView<ImageView>(R.id.item_img).apply{
                setOnItemChildClickListener { adapter, view, position ->
                    LogUtils.i(data[position].url)
                }
            })
    }
}