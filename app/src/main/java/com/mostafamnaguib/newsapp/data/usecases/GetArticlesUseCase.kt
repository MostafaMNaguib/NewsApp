package com.mostafamnaguib.newsapp.data.usecases

import com.mostafamnaguib.newsapp.data.pojo.response.ArticlesResponse
import com.mostafamnaguib.newsapp.data.repos.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetArticlesUseCase(
    private val repository: NewsRepository
)
{

    fun getArticles(): Single<ArticlesResponse>{

        return repository.getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}