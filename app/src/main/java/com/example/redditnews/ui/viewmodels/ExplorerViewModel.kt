package com.example.redditnews.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditnews.data.network.NetworkStatus
import com.example.redditnews.domain.repository.ExplorerRepository
import com.example.redditnews.ui.viewState.ExplorerViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val application       : Application
    ) : ViewModel() {

    private var _getExplorerArticles = MutableStateFlow<ExplorerViewState>(ExplorerViewState.Idle)
    var getHomeArticles : Flow<ExplorerViewState> = _getExplorerArticles

    init {
       getExplorerArticles()
    }

    private fun getExplorerArticles() = viewModelScope.launch {
        val statusOfNetwork =   NetworkStatus.isNetworkAvailable(application)
        if (statusOfNetwork)
        {
            explorerRepository.getExplorerArticles()
                .catch { e ->
                    _getExplorerArticles.emit(ExplorerViewState.Error(message = e.toString()))
                    Log.d("testApp" ,  e.message.toString())
                }
                .collect{ response ->
                  if (response.isSuccessful)
                  {
                      response.body()?.data?.children?.let {
                          _getExplorerArticles.emit(ExplorerViewState.Success(it))

                      } ?: _getExplorerArticles.emit(ExplorerViewState.EmptyData)
                  }
                    else
                  {
                      Log.d("testApp" , "response code articles explorer" + response.code().toString())
                  }
                }
        }
        else
        {
            Log.d("testApp" , "There is no internet connection")
        }

    }

}