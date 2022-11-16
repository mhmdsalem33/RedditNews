package com.example.redditnews.ui.viewState

import com.example.redditnews.domain.models.Children

sealed class HomeViewState {
    object Idle                                    : HomeViewState()
    object Loading                                 : HomeViewState()
    data class Success(val data : List<Children> ) : HomeViewState()
    data class Error(val message : String)         : HomeViewState()
}