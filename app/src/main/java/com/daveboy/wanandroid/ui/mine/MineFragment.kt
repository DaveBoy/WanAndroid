package com.daveboy.wanandroid.ui.mine

import androidx.lifecycle.Observer
import com.daveboy.base.BaseFragment
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment:BaseVMFragment<MineViewModel>(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
    }

    override fun initListener() {
        tv_score_title.setOnClickListener {

        }
    }

    override fun initData() {
        viewModel.getScore()
    }

    override fun startObserve() {
        viewModel.score.observe(this, Observer {
            parseState(it,{
                tv_score.text=it.coinCount.toString()
                tv_grade.text=it.level.toString()
                tv_sort.text=it.rank.toString()
                tv_id.text=it.userId.toString()
            })
        })
    }

}