package com.daveboy.wanandroid.ui.project.content

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.Article
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.smart_ly
import kotlinx.android.synthetic.main.fragment_tab_project.*

class ProjectContentFragment  : BaseVMFragment<ContentViewModel>(){
    private val adapter:ContentAdapter by lazy { ContentAdapter() }

    override fun startObserve() {
        viewModel.apply {
            tabProjectResponse.observe(this@ProjectContentFragment, Observer {
                smart_ly.finish()
                parseState(it,{
                    smart_ly.setEnableLoadMore(it.over.not())
                    if(viewModel.page==2){
                        adapter.setNewData(it.datas)
                    }else{
                        adapter.addData(it.datas)
                    }
                })

            })

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab_project
    }

    override fun initView() {
        viewModel.cid=arguments?.getInt("cid")

        tab_project_rv.layoutManager= LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(tab_project_rv)
        tab_project_rv.adapter=adapter.apply {
            this.setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.project_like->{
                        (adapter.data[position] as Article).let {
                            if(it.collect) viewModel.unCollect(it.id) else viewModel.collect(it.id)
                            it.collect=!it.collect
                        }

                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

        smart_ly.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadData(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                loadData(true)
            }
        })
    }

    override fun initListener() {
    }

    override fun initData() {
        loadData(true)
    }
    private fun loadData(refresh:Boolean){
        viewModel.getTabProjectResponse(refresh)

    }
}