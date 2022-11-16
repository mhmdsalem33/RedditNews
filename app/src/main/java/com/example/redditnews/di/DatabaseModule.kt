package com.example.redditnews.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.redditnews.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app  : Application) : ArticleDatabase =
        Room.databaseBuilder(app , ArticleDatabase::class.java , "article.db").build()
}