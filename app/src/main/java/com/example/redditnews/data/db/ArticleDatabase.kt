package com.example.redditnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.redditnews.domain.models.Article
import com.example.redditnews.domain.models.ArticleFavorite


@TypeConverters(TypeConverter::class)
@Database(entities = [Article::class  , ArticleFavorite::class ] , version = 1)
abstract class ArticleDatabase() : RoomDatabase() {
    abstract fun articleDao()    : ArticleDao
    abstract fun favoriteDao()   : FavoriteDao
}

