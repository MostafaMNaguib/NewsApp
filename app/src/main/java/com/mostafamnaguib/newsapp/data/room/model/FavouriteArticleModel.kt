package com.mostafamnaguib.newsapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavouriteArticleTable")
data class FavouriteArticleModel(

    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo val apiUrl: String,
    @ColumnInfo val isHosted: Boolean,
    @ColumnInfo val pillarId: String,
    @ColumnInfo val pillarName: String,
    @ColumnInfo val sectionId: String,
    @ColumnInfo val sectionName: String,
    @ColumnInfo val type: String,
    @ColumnInfo val webPublicationDate: String,
    @ColumnInfo val webTitle: String,
    @ColumnInfo val webUrl: String

)