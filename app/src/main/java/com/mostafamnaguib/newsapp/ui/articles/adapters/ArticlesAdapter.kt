package com.mostafamnaguib.newsapp.ui.articles.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mostafamnaguib.newsapp.data.pojo.response.Result
import com.mostafamnaguib.newsapp.databinding.ItemArticleBinding
import com.mostafamnaguib.newsapp.utils.RecyclerViewOnClickListener
import java.util.*
import kotlin.collections.ArrayList

class ArticlesAdapter(
    val listener: RecyclerViewOnClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>()
{

    private var list = ArrayList<Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun bind(result: Result,position: Int){

            binding.titleTv.text = result.webTitle
            binding.categoryTv.text = result.sectionName
            binding.dateTv.text = result.webPublicationDate

            binding.favoritesBtn.setOnClickListener {

                listener.onFavouriteClickListener(result,position,this,this)

            }

        }

    }

    fun submitData(d: ArrayList<Result>) {
        this.list = d
        notifyDataSetChanged()
    }

    fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }


}