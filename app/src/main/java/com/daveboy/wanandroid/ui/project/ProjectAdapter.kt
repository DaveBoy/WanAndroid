package com.daveboy.wanandroid.ui.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProjectAdapter(manager:FragmentManager) : FragmentStateAdapter(manager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var fragments:List<Fragment> = emptyList()
    var titles:List<String> = emptyList()
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position].replace("&amp;","&")
    }

}