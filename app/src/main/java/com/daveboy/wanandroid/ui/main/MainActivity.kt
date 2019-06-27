package com.daveboy.wanandroid.ui.main

import android.view.MenuItem
import com.daveboy.base.BaseActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexFragment
import com.daveboy.wanandroid.ui.project.ProjectFragment
import com.daveboy.wanandroid.ui.system.SystemFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment




class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.navigation_home -> showHideFragment(fragments[0])
            R.id.navigation_project ->showHideFragment(fragments[1])
            R.id.navigation_system -> showHideFragment(fragments[2])
            R.id.navigation_notifications -> showHideFragment(fragments[3])
        }
        return true
    }

    private  val fragments =  arrayOfNulls<SupportFragment>(4)

    override fun getLayoutId()= R.layout.activity_main
    override fun initView() {
        val fragment=findFragment(IndexFragment::class.java)
        if(fragment==null){
            fragments[0]=IndexFragment()
            fragments[1]=ProjectFragment()
            fragments[2]=SystemFragment()
            loadMultipleRootFragment(R.id.main_fraly, 0,fragments[0]!!, fragments[1]!!,fragments[2]!!)
        }else{
            fragments[0]=fragment
            fragments[1]=findFragment(ProjectFragment::class.java)
            fragments[2]=findFragment(SystemFragment::class.java)
        }
        nav_view.setOnNavigationItemSelectedListener(this)
    }

    override fun initListener() {
    }

    override fun initData() {
    }


}
