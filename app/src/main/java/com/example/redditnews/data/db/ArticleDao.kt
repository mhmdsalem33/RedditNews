package com.example.redditnews.data.db

import androidx.room.*
import com.example.redditnews.domain.models.Article
import com.example.redditnews.domain.models.ArticleFavorite
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun upsertArticles(article: List<Article>)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("DELETE FROM articleInformation")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM articleInformation")
    fun getAllSavedArticles() : Flow<List<Article>>



}

