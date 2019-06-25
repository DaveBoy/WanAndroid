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
}