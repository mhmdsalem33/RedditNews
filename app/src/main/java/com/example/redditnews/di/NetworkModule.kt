package com.example.redditnews.di
import com.example.redditnews.data.network.RedditApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi() : RedditApi =
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/r/kotlin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditApi::class.java)

}