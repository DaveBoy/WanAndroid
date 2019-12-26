package com.daveboy.wanandroid.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.project.content.ProjectContentFragment
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment :BaseVMFragment<ProjectViewModel>(){
    private lateinit var projectAdapter: ProjectAdapter
    override fun startObserve() {
        viewModel.apply {
            tabList.observe(this@ProjectFragment, Observer {list->
                val fragments=ArrayList<Fragment>()
                projectAdapter.titles=list.map {
                    fragments.add(ProjectContentFragment().apply {
                        arguments= Bundle().apply {
                            putInt("cid",it.id)
                        }
                    })
                    it.name
                }
                projectAdapter.fragments=fragments
                projectAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        projectAdapter= ProjectAdapter(childFragmentManager)
        project_vp.adapter=projectAdapter
        project_tab.setupWithViewPager(project_vp)
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.getTabList()
    }


}