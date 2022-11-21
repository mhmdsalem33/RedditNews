package com.example.redditnews.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditnews.R
import com.example.redditnews.data.realmdb.ArticleDatabaseExplorer
import com.example.redditnews.databinding.FragmentExplorerBinding
import com.example.redditnews.domain.models.ArticleFavorite
import com.example.redditnews.ui.adapters.ArticlesExplorerAdapter
import com.example.redditnews.ui.viewState.ExplorerViewState
import com.example.redditnews.ui.viewmodels.ExplorerViewModel
import com.example.redditnews.ui.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import io.realm.RealmConfiguration


@AndroidEntryPoint
class ExplorerFragment : Fragment() {

    private lateinit var binding : FragmentExplorerBinding
    private val explorerMvvm     : ExplorerViewModel by viewModels()
    private lateinit var articlesExplorerAdapter : ArticlesExplorerAdapter
    private val favoriteMvvm : FavoriteViewModel by viewModels()

    var realmId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentExplorerBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRealmDatabase()
        setupExplorerArticlesRecView()
        fetchArticlesDataAndSaveIt()
        onArticlesExplorerClick()
        onFavoriteExplorerClick()
    }



    private fun initRealmDatabase() {
        Realm.init(requireContext())
        val config     = RealmConfiguration.Builder().name("articles.realm").build()
        val realm      = Realm.getInstance(config)
        val savedData  = realm.where(ArticleDatabaseExplorer::class.java).findAll()
        articlesExplorerAdapter =   ArticlesExplorerAdapter(savedData)
    }

    private fun setupExplorerArticlesRecView() {
        binding.articlesRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter       = articlesExplorerAdapter
        }
    }


    private fun fetchArticlesDataAndSaveIt() {
        lifecycleScope.launchWhenStarted {
            explorerMvvm.getHomeArticles.collect{
                when(it)
                {
                    is ExplorerViewState.Loading -> { Log.d("testApp" , "Explore  articles is Loading") }
                    is ExplorerViewState.Success -> {

                        val config  = RealmConfiguration.Builder().name("articles.realm").build()
                        val realm   = Realm.getInstance(config)
                            realmId = realm.where(ArticleDatabaseExplorer::class.java).findAll().size

                        if (realm.where(ArticleDatabaseExplorer::class.java).findAll().isEmpty())
                        {
                            realm.beginTransaction()
                            for (i in it.data)
                            {
                                val saved = realm.createObject(ArticleDatabaseExplorer::class.java , realmId++)
                                    saved.id               = i.data.id
                                    saved.title            = i.data.title
                                    saved.thumbnail        = i.data.thumbnail
                            }
                            realm.commitTransaction()
                            setupExplorerArticlesRecView()
                        }
                        if (realm.where(ArticleDatabaseExplorer::class.java).findAll().size != it.data.size)
                        {
                            realm.beginTransaction()
                            realm.deleteAll()
                            for (i in it.data)
                            {
                                val saved = realm.createObject(ArticleDatabaseExplorer::class.java , realmId++)
                                    saved.id               = i.data.id
                                    saved.title            = i.data.title
                                    saved.thumbnail        = i.data.thumbnail
                            }
                            realm.commitTransaction()
                            setupExplorerArticlesRecView()
                        }
                    }
                    is ExplorerViewState.Error -> {
                        Log.d("testApp" , "articles explore ${it.message}")
                    }
                    is ExplorerViewState.EmptyData ->{
                        Log.d("testApp" , "Empty data")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun onArticlesExplorerClick() {
        articlesExplorerAdapter.onArticleExploreClick = { articleDatabase ->
            val fragment = ArticleFragment()
            val bundle   = Bundle()
                bundle.putString("title" , articleDatabase.title)
            fragment.arguments = bundle
            findNavController().navigate(R.id.articleFragment , bundle)
        }
    }


    private fun onFavoriteExplorerClick() {
        articlesExplorerAdapter.onArticleFavoriteExploreClick = {
            val articles = ArticleFavorite(id =   it.id.toString() , title = it.title)
           lifecycleScope.launchWhenStarted {
               favoriteMvvm.upsertFavoriteArticles(articles)
               Toast.makeText(requireContext(), "Article Saved", Toast.LENGTH_SHORT).show()
           }
        }
    }

}