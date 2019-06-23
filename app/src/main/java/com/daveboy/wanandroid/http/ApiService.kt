package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.database.ArticleResponse
import com.daveboy.wanandroid.database.BannerResponse
import com.daveboy.wanandroid.database.UserModel
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    suspend fun login(@Field("username")username:String, @Field("password")password:String): WanResponse<UserModel>

    @GET(Urls.ARTICLE_LIST)
    suspend fun getArticleList(@Path("page")page:Int): WanResponse<ArticleResponse>

    @GET(Urls.BANNER_LIST)
    suspend fun getBannerList(): WanResponse<List<BannerResponse>>
}