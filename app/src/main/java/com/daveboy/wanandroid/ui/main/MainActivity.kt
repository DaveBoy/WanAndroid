package com.daveboy.wanandroid.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daveboy.base.BaseActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexFragment
import com.daveboy.wanandroid.ui.project.ProjectFragment
import com.daveboy.wanandroid.ui.system.SystemWrapperFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private  val fragments:List<Fragment>
    init {
        val index=get<IndexFragment>()
        val project=get<ProjectFragment>()
        val system=get<SystemWrapperFragment>()
        fragments= listOf(index,project,system)
    }



    private fun updateCurrentVp(current:Int){
        main_vp2.setCurrentItem(current,true)
    }

    override fun getLayoutId()= R.layout.activity_main
    override fun initView() {
        main_vp2.adapter=object :FragmentStateAdapter(supportFragmentManager,lifecycle){
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }

        }
        main_vp2.isUserInputEnabled=false
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
