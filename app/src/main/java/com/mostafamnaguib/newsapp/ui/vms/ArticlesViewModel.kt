package com.mostafamnaguib.newsapp.ui.vms

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mostafamnaguib.newsapp.data.pojo.ErrorModel
import com.mostafamnaguib.newsapp.data.pojo.response.ArticlesResponse
import com.mostafamnaguib.newsapp.data.pojo.response.Response
import com.mostafamnaguib.newsapp.data.room.RoomManager
import com.mostafamnaguib.newsapp.data.room.model.FavouriteArticleModel
import com.mostafamnaguib.newsapp.data.usecases.GetArticlesUseCase
import com.mostafamnaguib.newsapp.utils.viewSnackBar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ArticlesViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val roomManager: RoomManager
):ViewModel()
{

    private val tAG = ArticlesViewModel::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()


    var articlesResponse = ArticlesResponse(
        Response(
            0,"",0,0, listOf(),0,"",0,""
        )
    )

    private val _favoritesMutableLiveData = MutableLiveData<List<FavouriteArticleModel>>()
    val favoritesMutableLiveData = _favoritesMutableLiveData

    private val _deleteFavoriteMutableLiveData = MutableLiveData<Unit>()
    val deleteFavoriteMutableLiveData = _deleteFavoriteMutableLiveData



    val isFinished: MutableLiveData<ErrorModel?> = MutableLiveData()


    fun getArticles(){

        getArticlesUseCase.getArticles()
            .subscribeWith(ArticlesSubscriber())
            .also(compositeDisposable::add)

    }

    inner class ArticlesSubscriber :
        DisposableSingleObserver<ArticlesResponse>() {
        override fun onSuccess(t: ArticlesResponse) {
            articlesResponse = t
            Log.e(tAG, "setUi: $t" )

            isFinished.postValue(
                ErrorModel(
                    true,200,"OK"
                )
            )

        }

        override fun onError(e: Throwable?) {
            Log.e(tAG,"onError: ${e?.message!!} - ${e.localizedMessage} ${e.cause}")

            if (e is SocketException || e is UnknownHostException)
            {
                Log.e(tAG, "onError:SocketException ${e.message} " )

                isFinished.postValue(
                    ErrorModel(
                        false,600,"OK"
                    )
                )

            }

            if(e is SocketTimeoutException)
            {
                Log.e(tAG, "onError:SocketException ${e.message} " )
                isFinished.postValue(
                    ErrorModel(
                        false,602,"OK"
                    )
                )
            }

            if (e is HttpException){
                Log.e(tAG, "onError:HttpException ${e.message} " )

                isFinished.postValue(
                    ErrorModel(false,
                        e.code(),
                        e.response()?.message()!!)
                )

            }
        }
    }

    fun insertFavorite(data: FavouriteArticleModel,view: View,resources: Resources){

        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {

                roomManager.getFavoritesDao().insertFavorite(data)

            }.onFailure {

            }.onSuccess {
                Log.e(tAG, "insertFavorite: Success" )
                viewSnackBar(view,resources,"This article has been added to favorites ")
            }
        }

    }

    fun getFavorites(){

        CoroutineScope(Dispatchers.IO).launch {

            kotlin.runCatching {
                roomManager.getFavoritesDao().getFavorites()
            }.onFailure {

            }.onSuccess {

                _favoritesMutableLiveData.postValue(it)
                Log.e(tAG, "getFavorites: " )
            }

        }

    }

    fun deleteFavorite(id: Int,view: View,resources: Resources){

        CoroutineScope(Dispatchers.IO).launch {

            kotlin.runCatching {
                roomManager.getFavoritesDao().deleteFavoriteById(id)
            }.onFailure {

            }.onSuccess {

                _deleteFavoriteMutableLiveData.postValue(it)
                viewSnackBar(view,resources,"This article has been removed from favorites ")

                Log.e(tAG, "deleteFavorites: $it" )
            }

        }

    }


}