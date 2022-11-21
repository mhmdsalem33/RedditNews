package com.example.redditnews.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditnews.domain.models.ArticleFavorite
import com.example.redditnews.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor( private val favoriteRepository: FavoriteRepository ) : ViewModel() {



    suspend fun upsertFavoriteArticles(articleFavorite: ArticleFavorite) = viewModelScope.launch {
             favoriteRepository.upsertFavoriteArticles(articleFavorite)
    }
    suspend fun deleteFavoriteArticles(articleFavorite: ArticleFavorite) = viewModelScope.launch {
        favoriteRepository.deleteFavoriteArticles(articleFavorite)
    }
    fun getAllSavedFavoriteArticles() = favoriteRepository.getAllSavedFavoriteArticles

}