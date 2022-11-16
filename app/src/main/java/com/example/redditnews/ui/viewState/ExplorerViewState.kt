package com.example.redditnews.ui.viewState

import com.example.redditnews.domain.models.Children

sealed class ExplorerViewState {
    object Idle                                      : ExplorerViewState()
    object Loading                                   : ExplorerViewState()
    data class Success(val data : List<Children> )   : ExplorerViewState()
    data class Error(val message : String)           : ExplorerViewState()
    object EmptyData                                 : ExplorerViewState()
}