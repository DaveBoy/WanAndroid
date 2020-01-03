package com.daveboy.wanandroid.util

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

fun RecyclerView.closeChangeAnimation() {
    (this.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
}