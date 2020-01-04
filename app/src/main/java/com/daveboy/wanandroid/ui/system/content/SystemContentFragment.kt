package com.daveboy.wanandroid.ui.system.content

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.common.util.extraFrag
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.Article
import com.daveboy.wanandroid.ui.main.index.ArticleAdapter
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_system_article.*

class SystemContentFragment :BaseVMFragment<SystemContentViewModel>(){
    private val cid by extraFrag("cid",-1)
    private val adapter by lazy { ArticleAdapter() }
    override fun startObserve() {
        viewModel.articleResponse.observe(this, Observer {
            smart_ly.finish()
            parseState(it,{
                if(viewModel.page==1){
                    adapter.setNewData(it.datas)
                }else{
                    adapter.addData(it.datas)
                }
            })
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_article
    }

    override fun initView() {
        article_rv.adapter=adapter.apply {
            this.setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.item_like->{
                        (adapter.data[position] as Article).let {
                            if(it.collect) viewModel.unCollect(it.id) else viewModel.collect(it.id)
                            it.collect=!it.collect
                        }

                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        article_rv.layoutManager=LinearLayoutManager(activity)
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getArticle()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getArticle(true)
            }

        })
    }

    override fun initData() {
        getArticle(true)
    }
    private fun getArticle(refresh:Boolean=false){
        viewModel.getSystemArticleList(refresh,cid)
    }

}