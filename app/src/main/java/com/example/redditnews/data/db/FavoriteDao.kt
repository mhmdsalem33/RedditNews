package com.example.redditnews.data.db

import androidx.room.*
import com.example.redditnews.domain.models.ArticleFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun upsertFavoriteArticles(article : ArticleFavorite)

    @Delete
    suspend fun deleteFavoriteArticles(article: ArticleFavorite)

    @Query("SELECT * FROM favoriteArticles")
    fun getAllFavoriteArticles() : Flow<List<ArticleFavorite>>
}