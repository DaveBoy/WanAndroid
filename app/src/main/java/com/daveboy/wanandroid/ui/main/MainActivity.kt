package com.daveboy.wanandroid.ui.main

import com.daveboy.base.BaseActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.ui.IndexFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : BaseActivity() {
    private  val fragments =  arrayOfNulls<SupportFragment>(4)

    override fun getLayoutId()= R.layout.activity_main
    override fun initView() {
        val fragment=findFragment(IndexFragment::class.java)
        if(fragment==null){
            fragments[0]=IndexFragment()
            loadRootFragment(R.id.main_fraly, fragments[0]!!)
        }else{
            fragments[0]=fragment
        }
    }

    override fun initListener() {
    }

    override fun initData() {
    }


}
