package com.mostafamnaguib.newsapp.ui.articles

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mostafamnaguib.newsapp.R
import com.mostafamnaguib.newsapp.data.pojo.response.Result
import com.mostafamnaguib.newsapp.data.room.model.FavouriteArticleModel
import com.mostafamnaguib.newsapp.databinding.FragmentViewArticlesBinding
import com.mostafamnaguib.newsapp.ui.MainActivity
import com.mostafamnaguib.newsapp.ui.articles.adapters.ArticlesAdapter
import com.mostafamnaguib.newsapp.ui.articles.adapters.FavouritesAdapter
import com.mostafamnaguib.newsapp.ui.vms.ArticlesViewModel
import com.mostafamnaguib.newsapp.utils.RecyclerViewOnClickListener
import com.mostafamnaguib.newsapp.utils.dialogs.LoadingDialog

class ViewArticlesFragment : Fragment(), RecyclerViewOnClickListener {

    private lateinit var binding: FragmentViewArticlesBinding
    private lateinit var mContext: Context
    private lateinit var navController: NavController
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var favouritesAdapter: FavouritesAdapter
    private lateinit var loadingDialog: LoadingDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewArticlesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = binding.root.context
        viewModel = (activity as MainActivity).viewModel

        loadingDialog = LoadingDialog(mContext)
        articlesAdapter = ArticlesAdapter(this)
        favouritesAdapter = FavouritesAdapter(this,mContext)

        viewModel.getArticles()
        loadingDialog.show()


        subscribeToObserver()
        setUi()
    }

    private fun setUi(){

        val toolbar = binding.toolbar

        toolbar.viewArticlesBtn.setBackgroundColor(resources.getColor(R.color.teal_700))
        toolbar.viewArticlesBtn.setTextColor(resources.getColor(R.color.white))

        toolbar.viewArticlesBtn.setOnClickListener {

            toolbar.viewArticlesBtn.setBackgroundColor(resources.getColor(R.color.teal_700))
            toolbar.viewArticlesBtn.setTextColor(resources.getColor(R.color.white))

            toolbar.viewFavoritesBtn.setBackgroundColor(resources.getColor(R.color.white))
            toolbar.viewFavoritesBtn.setTextColor(resources.getColor(R.color.black))

            binding.articlesRv.visibility = VISIBLE
            binding.favouritesRv.visibility = GONE

        }

        toolbar.viewFavoritesBtn.setOnClickListener {

            toolbar.viewFavoritesBtn.setBackgroundColor(resources.getColor(R.color.teal_700))
            toolbar.viewFavoritesBtn.setTextColor(resources.getColor(R.color.white))

            toolbar.viewArticlesBtn.setBackgroundColor(resources.getColor(R.color.white))
            toolbar.viewArticlesBtn.setTextColor(resources.getColor(R.color.black))

            binding.articlesRv.visibility = GONE
            binding.favouritesRv.visibility = VISIBLE

            subscribeToObserver()

        }


    }

    override fun onFavouriteClickListener(
        result: Result,
        position: Int,
        viewHolder: RecyclerView.ViewHolder,
        viewHolder2: ArticlesAdapter.ViewHolder
    ) {
        super.onFavouriteClickListener(result, position, viewHolder, viewHolder2)
        viewModel.insertFavorite(
            FavouriteArticleModel(
                0,result.apiUrl,result.isHosted,result.pillarId,result.pillarName,
                result.sectionId,result.sectionName,result.type,result.webPublicationDate,result.webTitle,result.webUrl
            ),binding.root,resources
        )
    }

    private fun subscribeToObserver(){

        binding.articlesRv.layoutManager = LinearLayoutManager(mContext)
        binding.favouritesRv.layoutManager = LinearLayoutManager(mContext)

        viewModel.getFavorites()
        viewModel.favoritesMutableLiveData.observe(viewLifecycleOwner){

            if (it.isNotEmpty())
            {
                favouritesAdapter.submitData(it as ArrayList<FavouriteArticleModel>)
                binding.favouritesRv.adapter = favouritesAdapter
            }

        }

        viewModel.isFinished.observe(viewLifecycleOwner){

            when(it?.isFinished){

                true->{

                    loadingDialog.dismiss()
                    articlesAdapter.submitData(viewModel.articlesResponse.response.results as ArrayList<Result>)
                    binding.articlesRv.adapter = articlesAdapter

                }

            }

        }

    }

}