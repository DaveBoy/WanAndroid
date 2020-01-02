package com.daveboy.wanandroid.ui.system

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.SystemModel
import com.google.android.flexbox.FlexboxLayout

class SystemAdapter:BaseQuickAdapter<SystemModel,BaseViewHolder>(R.layout.item_system) {
    val textParm= ViewGroup.MarginLayoutParams(WRAP_CONTENT,WRAP_CONTENT).apply {
        leftMargin=ConvertUtils.dp2px(20f)
        topMargin=ConvertUtils.dp2px(10f)
    }
    override fun convert(helper: BaseViewHolder, item: SystemModel) {
        helper.setText(R.id.system_title,item.name)
        val container=helper.getView<FlexboxLayout>(R.id.system_des)
        container.removeAllViews()
        item.children.forEachIndexed { index, systemTopic ->
            val textView = TextView(mContext).apply {
                text = systemTopic.name
            }
            container.addView(textView,index,textParm)
        }
    }
}