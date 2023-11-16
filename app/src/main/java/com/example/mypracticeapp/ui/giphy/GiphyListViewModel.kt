package com.example.mypracticeapp.ui.giphy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.GifModel
import com.example.domain.usecase.RetrieveTrendingGifsUseCase
import com.example.mypracticeapp.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyListViewModel @Inject constructor(
    private val retrieveTrendingGifsUseCase: RetrieveTrendingGifsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<GiphyListUiState>(GiphyListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentOffset = 0

    init {
        getTrendingGifs()
    }

    fun getTrendingGifs() {
        viewModelScope.launch(errorHandler()) {
            val trendingGifs = retrieveTrendingGifsUseCase(
                apiKey = API_KEY,
                limit = TRENDING_GIF_LIMIT,
                offset = currentOffset
            )
            _uiState.value = GiphyListUiState.Success(
                uiModel = GiphyListUiModel(
                    gifs = processPagination(trendingGifs)
                )
            )
            currentOffset += TRENDING_GIF_LIMIT
        }
    }

    private fun processPagination(list: List<GifModel>): List<GifModel> {
        return when (_uiState.value) {
            is GiphyListUiState.Success -> {
                (_uiState.value as GiphyListUiState.Success).uiModel.gifs.plus(list)
            }

            else -> {
                list
            }
        }
    }

    private fun errorHandler() = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = GiphyListUiState.Error
    }

    companion object {
        private const val API_KEY = BuildConfig.api_key
        private const val TRENDING_GIF_LIMIT = 25
    }
}