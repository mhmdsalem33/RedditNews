package com.example.redditnews.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditnews.data.network.NetworkStatus
import com.example.redditnews.domain.models.Article
import com.example.redditnews.domain.repository.HomeRepository
import com.example.redditnews.ui.viewState.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val homeRepository: HomeRepository ,
    private val application   : Application
    ) :ViewModel()  {


    private var _getArticlesStateFlow = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    var getArticlesStateFlow : StateFlow<HomeViewState> = _getArticlesStateFlow

    init {
          getArticles()
    }

     private fun getArticles() = viewModelScope.launch {
        try {
            _getArticlesStateFlow.emit(HomeViewState.Loading)
          val statusOfNetwork =   NetworkStatus.isNetworkAvailable(application)
            if (statusOfNetwork)
            {
                val response = homeRepository.getArticles()
                if (response.isSuccessful)
                {
                    response.body()?.data?.children?.let {
                        _getArticlesStateFlow.emit(HomeViewState.Success(it))

                    } ?: _getArticlesStateFlow.emit(HomeViewState.EmptyData)
                }
                else
                {
                    Log.d("testApp" ,"Home articles response code "+ response.code().toString())
                    _getArticlesStateFlow.emit(HomeViewState.Error(message = response.message().toString()))
                }
            }
            else
            {
                Log.d("testApp" , "There is no internet connection")
            }
        }catch (e : Exception)
        {
            _getArticlesStateFlow.emit(HomeViewState.Error(message = e.message.toString()))
            Log.d("testApp" , e.message.toString())
        }
    }

    suspend fun upsertArticle(article: List<Article>) = viewModelScope.launch { homeRepository.upsert(article) }
    fun getAllSavedArticles()                         = homeRepository.getAllSavedArticles



}