package com.daveboy.wanandroid.ui.main.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.database.Article
import com.daveboy.wanandroid.database.SearchKeyHot
import com.daveboy.wanandroid.database.Site

class HotKeyAdapter : BaseQuickAdapter<SearchKeyHot, BaseViewHolder>(R.layout.item_search_key_and_site) {

    override fun convert(helper: BaseViewHolder, item: SearchKeyHot) {
        helper.setText(R.id.item_key, item.name)
    }
}
class SiteAdapter : BaseQuickAdapter<Site, BaseViewHolder>(R.layout.item_search_key_and_site) {

    override fun convert(helper: BaseViewHolder, item: Site) {
        helper.setText(R.id.item_key, item.name)
    }
}