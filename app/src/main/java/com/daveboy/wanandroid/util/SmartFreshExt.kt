package com.daveboy.wanandroid.util

import com.scwang.smartrefresh.layout.SmartRefreshLayout

fun SmartRefreshLayout.finish(){
    this.finishRefresh()
    this.finishLoadMore()
}