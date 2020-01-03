package com.daveboy.wanandroid.http
object Urls {
    const val BASE_URL = "https://www.wanandroid.com"

    /**
     * 登录
     */
    const val LOGIN = "user/login"
    /**
     * 首页文章列表
     */
    const val ARTICLE_LIST = "article/list/{page}/json"
    /**
     * 首页置顶文章列表
     */
    const val TOP_ARTICLE_LIST = "article/top/json"
    /**
     * 首页Banner
     */
    const val BANNER_LIST = "banner/json"
    /**
     * 友链
     */
    const val SITE_LIST = "friend/json"
    /**
     * 热词
     */
    const val HOTKEY_LIST = "hotkey/json"
    /**
     * 文章搜索
     */
    const val ARTICLE_SEARCH_LIST = "article/query/{page}/json"

    /**
     * 项目分类
     */
    const val TAB_LIST = "project/tree/json"

    /**
     * tab对应的项目列表
     */
    const val TAB_PROJECT_LIST = "project/list/{page}/json"
    /**
     * 体系列表
     */
    const val SYSTEM_LIST = "tree/json"
    /**
     * 导航列表
     */
    const val NAVI_LIST = "navi/json"

    /**
     * 个人积分
     */
    const val SCORE = "lg/coin/userinfo/json"

}