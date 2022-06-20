package com.mostafamnaguib.newsapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mostafamnaguib.newsapp.data.room.model.FavouriteArticleModel

@Dao
interface ArticlesDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(data: FavouriteArticleModel)

    @Query("SELECT * FROM FavouriteArticleTable")
    fun getFavorites(): List<FavouriteArticleModel>

    @Query("SELECT * FROM FavouriteArticleTable WHERE id=:id")
    fun getFavorite(id: String) : FavouriteArticleModel

    @Query("DELETE FROM FavouriteArticleTable WHERE id = :id")
    fun deleteFavoriteById(id: Int)

}