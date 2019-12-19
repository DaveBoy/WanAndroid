package com.daveboy.wanandroid.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.daveboy.base.BaseActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexFragment
import com.daveboy.wanandroid.ui.project.ProjectFragment
import com.daveboy.wanandroid.ui.system.SystemFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private  val fragments:List<Fragment>
    init {
        val index=IndexFragment()
        val project=ProjectFragment()
        val system=SystemFragment()
        fragments= listOf(index,project,system)
    }



    private fun updateCurrentVp(current:Int){
        main_vp2.setCurrentItem(current,true)
    }

    override fun getLayoutId()= R.layout.activity_main
    override fun initView() {
        nav_view.setOnNavigationItemSelectedListener(this)
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> updateCurrentVp(0)
            R.id.navigation_project ->updateCurrentVp(1)
            R.id.navigation_system -> updateCurrentVp(2)
            R.id.navigation_notifications -> updateCurrentVp(3)
        }
        return true
    }
}
