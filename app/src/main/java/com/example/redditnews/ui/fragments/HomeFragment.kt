package com.example.redditnews.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditnews.R
import com.example.redditnews.databinding.FragmentHomeBinding
import com.example.redditnews.ui.activites.MainActivity
import com.example.redditnews.ui.adapters.ArticlesAdapter
import com.example.redditnews.ui.viewState.HomeViewState
import com.example.redditnews.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    /*
        In this task i have used two ways to fetch data and caching it into ( Room database ) in Home Fragment
        and caching it into ( Realm database ) in Explorer Fragment
        Technologies used
       * Kotlin
       * Mvvm
       * Coroutines
       * Room  database used in home fragment
       * Realm database used in explorer fragment
       * Dagger hilt
       * Retrofit
       * navigation component

        * i have used RedditApi to make calling for api with a suspend fun to run code into background
          by Coroutines and i handle it with HomerRepository and HomeViewModel

        * we will call api request when internet connection is available if there is no internet connection we will not call api
          object NetworkStatus will take care about internet connection


     */




    private lateinit var binding   : FragmentHomeBinding
    private val homeMvvm           : HomeViewModel by viewModels()
    private lateinit var articlesAdapter  : ArticlesAdapter
    private var articlesSavedCount        : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlesAdapter = ArticlesAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ( activity as MainActivity ).supportActionBar?.title = "Kotlin News"

        setupArticleRecView()
        observeArticlesData()
        fetchDataFromRoomDatabase()
        onArticleItemClick()
        onHandleOnBackPressed()

    }


    private fun setupArticleRecView() {
        binding.homeRecView.apply {
            layoutManager = LinearLayoutManager( requireContext() , RecyclerView.VERTICAL , false)
            adapter       = articlesAdapter
        }
    }

    private fun fetchDataFromRoomDatabase() {
        lifecycleScope.launchWhenStarted {
            homeMvvm.getAllSavedArticles().collect{ articles ->
                articlesAdapter.differ.submitList(articles)
                articlesSavedCount = articles.size
            }
        }
    }

    private fun observeArticlesData() {
        lifecycleScope.launchWhenStarted {
            homeMvvm.getArticlesStateFlow.collect {
                when(it)
                {
                    is HomeViewState.Loading -> { Log.d("testApp" , "articles home is loading") }
                    is HomeViewState.Success -> {
                        if (articlesSavedCount ==  0)
                        {
                          for (article in it.data)
                          {
                              homeMvvm.upsertArticle(listOf(article.data))
                          }
                        }
                    }
                    is HomeViewState.Error   -> { Log.d("testApp" , it.message) }
                    else -> Unit
                }
            }
        }
    }

    private fun onArticleItemClick() {
       articlesAdapter.onItemArticleClick = { article ->
           val fragment = ArticleFragment()
           val bundle   = Bundle()
               bundle.putString("title" , article.title)
           // TODO: 10/16/2022  there is no thumbnail found in api it's contains empty values
           Log.d("testApp" , article.thumbnail)
           fragment.arguments = bundle
           findNavController().navigate(R.id.action_homeFragment_to_articleFragment  , bundle )
       }
    }

    private fun onHandleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
              activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner , callback)
    }

}