package com.daveboy.wanandroid.ui.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProjectAdapter(manager:FragmentManager,lifecycle:Lifecycle) : FragmentStateAdapter(manager,lifecycle) {
    var fragments:List<Fragment> = emptyList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}