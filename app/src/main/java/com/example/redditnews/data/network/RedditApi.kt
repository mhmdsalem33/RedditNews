package com.example.redditnews.data.network

import com.example.redditnews.domain.models.ArticleList
import retrofit2.Response
import retrofit2.http.GET

interface RedditApi {
    @GET(".json")
    suspend fun getArticles() : Response<ArticleList>
}