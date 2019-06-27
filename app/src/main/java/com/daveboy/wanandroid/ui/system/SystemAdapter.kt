package com.daveboy.wanandroid.ui.system

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.database.SystemModel
import com.google.android.flexbox.FlexboxLayout

class SystemAdapter:BaseQuickAdapter<SystemModel,BaseViewHolder>(R.layout.item_system) {
    val laParm= ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
    override fun convert(helper: BaseViewHolder, item: SystemModel) {
        helper.setText(R.id.system_title,item.name)
        val container=helper.getView<FlexboxLayout>(R.id.system_des)
        item.children.forEachIndexed { index, systemTopic ->
            val textView = TextView(mContext).apply {
                text = systemTopic.name
            }
            container.addView(textView,index,laParm)
        }
    }
}