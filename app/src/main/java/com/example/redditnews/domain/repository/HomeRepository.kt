package com.example.redditnews.domain.repository


import com.example.redditnews.data.db.ArticleDatabase
import com.example.redditnews.domain.models.Article
import com.example.redditnews.data.network.RedditApi
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val redditApi: RedditApi ,
    private val db       : ArticleDatabase
    )
{
    private val articleDao = db.articleDao()


    suspend fun getArticles()                  = redditApi.getArticles()
    suspend fun upsert(article: List<Article>) = articleDao.upsertArticles(article)
    val getAllSavedArticles                    = articleDao.getAllSavedArticles()
}