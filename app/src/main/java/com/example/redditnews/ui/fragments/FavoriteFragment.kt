package com.example.redditnews.ui.fragments

import android.os.Bundle
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
import com.example.redditnews.databinding.FragmentFavoriteBinding
import com.example.redditnews.ui.adapters.FavoriteAdapter
import com.example.redditnews.ui.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private val favoriteMvvm     : FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteAdapter = FavoriteAdapter()

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupFavoriteRecView()
        fetchFavoriteSavedArticles()
        onItemFavoriteClick()
        onItemFavoriteDelete()



    }



    private fun setupFavoriteRecView() {
        binding.favoriteRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter       = favoriteAdapter
        }
    }


    private fun fetchFavoriteSavedArticles() {
        lifecycleScope.launchWhenStarted {
            favoriteMvvm.getAllSavedFavoriteArticles().collect {
                favoriteAdapter.differ.submitList(it)
            }
        }
    }


    private fun onItemFavoriteClick() {
       favoriteAdapter.onItemFavoriteCLick = {
           val fragment = ArticleFragment()
           val bundle   = Bundle()
               bundle.putString("title" , it.title)
           fragment.arguments = bundle
           findNavController().navigate(R.id.articleFragment , bundle)
       }
    }


    private fun onItemFavoriteDelete() {
      favoriteAdapter.onDeleteFavoriteItemClick = {
          lifecycleScope.launchWhenStarted {
              favoriteMvvm.deleteFavoriteArticles(it)
              Toast.makeText(requireContext(), "Article removed success", Toast.LENGTH_SHORT).show()
          }
      }
    }


}