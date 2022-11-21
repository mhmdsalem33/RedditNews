package com.example.redditnews.domain.repository

import com.example.redditnews.data.network.RedditApi
import com.example.redditnews.domain.models.ArticleList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ExplorerRepository @Inject constructor(
    private val redditApi: RedditApi
)
{
    suspend fun getExplorerArticles() : Flow<Response<ArticleList>> = flow {
        val response =  redditApi.getArticles()
        emit(response)
    }.flowOn(Dispatchers.IO)
}