package com.daveboy.wanandroid.ui.main.ui.viewPaper

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.daveboy.wanandroid.database.BannerResponse
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.daveboy.wanandroid.R


class BannerAdapter( private val context:Context) :PagerAdapter(){
    private var data:MutableList<BannerResponse> = ArrayList()
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return data.size
    }
    fun setData(list:MutableList<BannerResponse>){
        if(list.isNullOrEmpty())
            return
        data.clear()
        data.add(list[list.size-1])
        data.addAll(list)
        data.add(list[0])
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView( `object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false)
        val imageView = view.findViewById(R.id.item_img) as ImageView

        val index = position % data.size
        Glide
            .with(context)
            .load(data[index].imagePath)
            .into(imageView)
        //OnClick
        imageView.setOnClickListener{
            LogUtils.i(data[position].url)
        }

        container.addView(view)
        return view
    }
}