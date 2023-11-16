package com.example.mypracticeapp.ui.giphy

sealed interface GiphyListUiState {

    data class Success(
        val uiModel: GiphyListUiModel
    ): GiphyListUiState

    object Loading: GiphyListUiState

    object Error: GiphyListUiState
}