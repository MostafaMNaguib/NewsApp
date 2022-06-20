package com.mostafamnaguib.newsapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mostafamnaguib.newsapp.data.room.model.FavouriteArticleModel

@Database(
    version = 1,
    entities = [FavouriteArticleModel::class],
    exportSchema = false
)

abstract class RoomManager : RoomDatabase() {

    abstract fun getFavoritesDao(): ArticlesDao

}