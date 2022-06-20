package com.mostafamnaguib.newsapp.utils

import androidx.recyclerview.widget.RecyclerView
import com.mostafamnaguib.newsapp.data.pojo.response.Result
import com.mostafamnaguib.newsapp.ui.articles.adapters.ArticlesAdapter


interface RecyclerViewOnClickListener {

    fun onFavouriteClickListener(result: Result,position: Int
                                ,viewHolder: RecyclerView.ViewHolder
                                ,viewHolder2: ArticlesAdapter.ViewHolder) {}



}