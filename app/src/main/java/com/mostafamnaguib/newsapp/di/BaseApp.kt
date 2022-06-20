package com.mostafamnaguib.newsapp.di

import android.app.Application
import androidx.room.Room
import com.mostafamnaguib.newsapp.data.room.RoomManager
import com.mostafamnaguib.newsapp.di.modules.appComponent
import com.mostafamnaguib.newsapp.utils.Constants
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get

class BaseApp: Application()
{

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { } // nothing or some logging
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@BaseApp)

        modules(appComponent)
    }

}