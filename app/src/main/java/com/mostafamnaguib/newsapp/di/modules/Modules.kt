package com.mostafamnaguib.newsapp.di.modules

import androidx.room.Room
import com.mostafamnaguib.newsapp.data.room.RoomManager
import com.mostafamnaguib.newsapp.utils.Constants
import org.koin.dsl.module


object Modules {


    val modules = module {
        single {
            Room.databaseBuilder(
                get(),
                RoomManager::class.java,
                Constants.DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }



    }


}