package com.mostafamnaguib.newsapp.ui.articles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mostafamnaguib.newsapp.R
import com.mostafamnaguib.newsapp.data.pojo.response.Result
import com.mostafamnaguib.newsapp.data.room.model.FavouriteArticleModel
import com.mostafamnaguib.newsapp.databinding.ItemArticleBinding
import com.mostafamnaguib.newsapp.utils.RecyclerViewOnClickListener

class FavouritesAdapter(
    val listener: RecyclerViewOnClickListener,val mContext: Context
): RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private var list = ArrayList<FavouriteArticleModel>()


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

        fun bind(result: FavouriteArticleModel, position: Int){

            binding.titleTv.text = result.webTitle
            binding.categoryTv.text = result.sectionName
            binding.dateTv.text = result.webPublicationDate
            binding.favoritesBtn.setImageDrawable(mContext.resources.getDrawable(R.drawable.ic_favorite_filled))

        }

    }

    fun submitData(d: ArrayList<FavouriteArticleModel>) {
        this.list = d
        notifyDataSetChanged()
    }

    fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

}