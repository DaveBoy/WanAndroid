package com.daveboy.wanandroid.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.project.content.ProjectContentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment :BaseVMFragment<ProjectViewModel>(){
    private lateinit var projectAdapter: ProjectAdapter
    private var titleList:List<String> = emptyList()
    override fun startObserve() {
        viewModel.apply {
            tabList.observe(this@ProjectFragment, Observer {
                parseState(it,{
                    projectAdapter.fragments=it.map {
                        ProjectContentFragment().apply {
                            arguments= Bundle().apply {
                                putInt("cid",it.id)
                            }
                        }
                    }
                    titleList=it.map { it.name.replace("&amp;","&") }

                    projectAdapter.notifyDataSetChanged()
                })

            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        projectAdapter= ProjectAdapter(childFragmentManager,lifecycle)
        project_vp.adapter=projectAdapter
        val tabMediator= TabLayoutMediator(project_tab,project_vp,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                tab.text = titleList[position]
            }
        )
        tabMediator.attach();
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.getTabList()
    }


}