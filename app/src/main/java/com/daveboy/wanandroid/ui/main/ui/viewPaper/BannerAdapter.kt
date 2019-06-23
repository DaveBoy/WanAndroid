package com.daveboy.wanandroid.ui.main.ui.viewPaper

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.daveboy.wanandroid.database.BannerResponse
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daveboy.wanandroid.R


class BannerAdapter( private val context:Context) :PagerAdapter(){
    var data:List<BannerResponse> = emptyList()
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return if(data.isNullOrEmpty()) 0 else Int.MAX_VALUE//data.size
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

        }

        container.addView(view)
        return view
    }
}