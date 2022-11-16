package com.example.redditnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.redditnews.domain.models.Article


@TypeConverters(TypeConverter::class)
@Database(entities = [Article::class] , version = 1)
abstract class ArticleDatabase() : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
}

