package com.daveboy.wanandroid.ui.mine.collect

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daveboy.base.BaseActivity
import com.daveboy.base.BaseFragment
import com.daveboy.wanandroid.R
import kotlinx.android.synthetic.main.fragment_collect_wrapper.*

class CollectActivity:BaseActivity() {
    private val list:List<Fragment>
    init {
        list= listOf(CollectArticleFragment())
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_collect_wrapper
    }

    override fun initView() {
        collect_vp.adapter=object:FragmentStateAdapter(this){
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