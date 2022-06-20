package com.mostafamnaguib.newsapp.data.repos

import com.mostafamnaguib.newsapp.data.pojo.response.ArticlesResponse
import com.mostafamnaguib.newsapp.network.ApiServices
import com.mostafamnaguib.newsapp.utils.Constants.ApiKey
import io.reactivex.rxjava3.core.Single

class NewsRepository(
    private val api: ApiServices
)
{

    private val tAG = NewsRepository::class.java.simpleName

    fun getArticles():Single<ArticlesResponse>{

        return api.getArticles(
            ApiKey
        )

    }

}