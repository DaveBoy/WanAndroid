package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.entity.*
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    suspend fun login(@Field("username")username:String, @Field("password")password:String): BaseResponse<UserModel>

    @GET(Urls.ARTICLE_LIST)
    suspend fun getArticleList(@Path("page")page:Int): BaseResponse<ArticleResponse>
    @GET(Urls.TOP_ARTICLE_LIST)
    suspend fun getTopArticleList(): BaseResponse<List<Article>>

    @GET(Urls.BANNER_LIST)
    suspend fun getBannerList(): BaseResponse<List<BannerResponse>>
    @GET(Urls.HOTKEY_LIST)
    suspend fun getHotKeyList(): BaseResponse<List<SearchKeyHot>>
    @GET(Urls.SITE_LIST)
    suspend fun getSiteList(): BaseResponse<List<Site>>

    @FormUrlEncoded
    @POST(Urls.ARTICLE_SEARCH_LIST)
    suspend fun searchArticleList(@Path("page")page:Int,@Field("k")keyWord:String): BaseResponse<ArticleResponse>

    @GET(Urls.TAB_LIST)
    suspend fun getTabList(): BaseResponse<List<ProjectTab>>

    @GET(Urls.TAB_PROJECT_LIST)
    suspend fun getTabProjectList(@Path("page")page:Int,@Query("cid")cid:Int): BaseResponse<TabProjectResponse>

    @GET(Urls.SYSTEM_LIST)
    suspend fun getSystemList(): BaseResponse<List<SystemModel>>
    @GET(Urls.NAVI_LIST)
    suspend fun getNavigationList(): BaseResponse<List<NavigationModel>>

    @GET(Urls.ARTICLE_LIST)
    suspend fun getSystemArticleList(@Path("page")page:Int,@Query("cid")cid:Int): BaseResponse<ArticleResponse>

    @GET(Urls.SCORE)
    suspend fun getScore(): BaseResponse<Score>

    @POST(Urls.COLLECT)
    suspend fun collect(@Path("id")id:Int): BaseResponse<String?>

    @POST(Urls.UNCOLLECT)
    suspend fun unCollect(@Path("id")id:Int): BaseResponse<String?>
}