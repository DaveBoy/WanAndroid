package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.database.*
import com.google.gson.JsonArray
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    suspend fun login(@Field("username")username:String, @Field("password")password:String): WanResponse<UserModel>

    @GET(Urls.ARTICLE_LIST)
    suspend fun getArticleList(@Path("page")page:Int): WanResponse<ArticleResponse>
    @GET(Urls.TOP_ARTICLE_LIST)
    suspend fun getTopArticleList(): WanResponse<List<TopArticleResponse>>

    @GET(Urls.BANNER_LIST)
    suspend fun getBannerList(): WanResponse<List<BannerResponse>>
    @GET(Urls.HOTKEY_LIST)
    suspend fun getHotKeyList(): WanResponse<List<SearchKeyHot>>
    @GET(Urls.SITE_LIST)
    suspend fun getSiteList(): WanResponse<List<Site>>

    @FormUrlEncoded
    @POST(Urls.ARTICLE_SEARCH_LIST)
    suspend fun searchArticleList(@Path("page")page:Int,@Field("k")keyWord:String): WanResponse<ArticleResponse>

    @GET(Urls.TAB_LIST)
    suspend fun getTabList(): WanResponse<List<ProjectTab>>

    @GET(Urls.TAB_PROJECT_LIST)
    suspend fun getTabProjectList(@Path("page")page:Int,@Query("cid")cid:Int): WanResponse<TabProjectResponse>

    @GET(Urls.SYSTEM_LIST)
    suspend fun getSystemList(): WanResponse<List<SystemModel>>
}