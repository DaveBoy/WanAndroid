package com.daveboy.wanandroid.ui.system.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daveboy.base.BaseActivity
import com.daveboy.common.util.extraAct
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.SystemModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_system_content.*

class SystemContentWrapperActivity: BaseActivity() {
    private val systemModel by extraAct<SystemModel>("data")
    private val index by extraAct("index",0)
    override fun getLayoutId(): Int {
        return R.layout.activity_system_content
    }

    override fun initView() {
        system_content_vp.adapter=object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return systemModel?.children?.size?:0
            }

            override fun createFragment(position: Int): Fragment {
                return SystemContentFragment().apply {
                    this.arguments= Bundle().apply {
                        putInt("cid",systemModel?.children?.get(position)?.id?:-1)
                    }
                }
            }

        }
        TabLayoutMediator(tably,system_content_vp,true,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                tab.text = systemModel?.children?.get(position)?.name?:""
            }).attach()
        system_content_vp.currentItem=index
    }

    override fun initListener() {

    }

    override fun initData() {
    }
}