package com.daveboy.wanandroid.ui.mine

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.daveboy.base.BaseFragment
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.common.util.Preference
import com.daveboy.common.util.startActivityExt
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.common.GlideApp
import com.daveboy.wanandroid.constant.LOGIN_STATE
import com.daveboy.wanandroid.constant.TOKEN_KEY
import com.daveboy.wanandroid.constant.USERNAME_KEY
import com.daveboy.wanandroid.ui.login.LoginActivity
import com.daveboy.wanandroid.ui.mine.collect.CollectActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment:BaseVMFragment<MineViewModel>(){
    private val name by Preference(USERNAME_KEY,"")
    private val token by Preference<String?>(TOKEN_KEY,"")

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        GlideApp.with(this).load(R.mipmap.ic_launcher).circleCrop().into(iv_header)
    }

    override fun initListener() {
        tv_name.setOnClickListener {
            if(token.isNullOrEmpty()) startActivityExt<LoginActivity>()
        }
        tv_collect.setOnClickListener {
            if(token.isNullOrEmpty()) startActivityExt<LoginActivity>()
            else startActivityExt<CollectActivity>()
        }
    }

    override fun initData() {
        if(!token.isNullOrEmpty()){
            viewModel.getScore()
        }
    }

    override fun startObserve() {
        viewModel.score.observe(this, Observer {
            parseState(it,{
                tv_score.text=it.coinCount.toString()
                tv_grade.text="等级:${it.level}"
                tv_sort.text="排行:${it.rank}"
                tv_id.text="ID:${it.userId}"
                tv_name.text=name
            })
        })
        LiveEventBus.get(LOGIN_STATE,Boolean::class.java).observe(this, Observer {
            initData()
        })
    }

}