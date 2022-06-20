package com.mostafamnaguib.newsapp.network

import com.mostafamnaguib.newsapp.data.pojo.response.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices
{

    @GET("search")
    fun getArticles(
        @Query("api-key") apiKey: String
    ): Single<ArticlesResponse>

    @GET("")
    fun getArticleByContent(

    )

}