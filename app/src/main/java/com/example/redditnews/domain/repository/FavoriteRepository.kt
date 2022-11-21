package com.example.redditnews.domain.repository

import com.example.redditnews.data.db.ArticleDatabase
import com.example.redditnews.domain.models.ArticleFavorite
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val db : ArticleDatabase) {


   private val favoriteDao = db.favoriteDao()

    suspend fun upsertFavoriteArticles(articles : ArticleFavorite) = favoriteDao.upsertFavoriteArticles(articles)
    suspend fun deleteFavoriteArticles(articles: ArticleFavorite)  = favoriteDao.deleteFavoriteArticles(articles)
    val  getAllSavedFavoriteArticles    = favoriteDao.getAllFavoriteArticles()
}