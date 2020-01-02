package com.daveboy.wanandroid.ui.system

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daveboy.base.BaseFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.system.navigation.NavigationFragment
import kotlinx.android.synthetic.main.fragment_system_wrapper.*

class SystemWrapperFragment:BaseFragment() {
    private val list:List<Fragment>
    init {
        list= listOf(SystemFragment(), NavigationFragment())
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_system_wrapper
    }

    override fun initView() {
        system_vp.adapter= object : FragmentStateAdapter(childFragmentManager,lifecycle) {
            override fun getItemCount(): Int {
                return list.size
            }

            override fun createFragment(position: Int): Fragment {
                return list[position]
            }
        }
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}