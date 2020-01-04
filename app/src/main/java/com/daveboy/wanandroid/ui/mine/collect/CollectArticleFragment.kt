package com.daveboy.wanandroid.ui.mine.collect

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daveboy.base.BaseFragment
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.Article
import com.daveboy.wanandroid.ui.main.index.ArticleAdapter
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_collect_article.*
import kotlinx.android.synthetic.main.fragment_collect_wrapper.*

class CollectArticleFragment:BaseVMFragment<CollectArticleViewModel>() {
    private val adapter by lazy { ArticleAdapter() }
    override fun startObserve() {
        viewModel.articleList.observe(this, Observer {
            smart_ly.finish()
            parseState(it,{
                it.datas.forEach { it.collect=true }
                if(viewModel.page==1)
                    adapter.setNewData(it.datas)
                else
                    adapter.addData(it.datas)
            })
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_collect_article
    }

    override fun initView() {
        article_rv.layoutManager=LinearLayoutManager(activity)
        article_rv.adapter=adapter.apply {
            this.setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.item_like->{
                        (adapter.data[position] as Article).let {
                            viewModel.unCollectInCollect(it.id,it.originId)
                        }
                        adapter.data.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        smart_ly.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getCollectArticle()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getCollectArticle(true)
            }

        })
    }

    override fun initListener() {
    }

    override fun initData() {
        getCollectArticle(true)
    }
    private fun getCollectArticle(refresh:Boolean=false){
        viewModel.getCollectArticle(refresh)
    }
}