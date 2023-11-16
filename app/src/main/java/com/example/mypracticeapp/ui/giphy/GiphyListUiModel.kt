package com.example.mypracticeapp.ui.giphy

import com.example.domain.model.GifModel

data class GiphyListUiModel(
    val gifs: List<GifModel>
) {

    companion object {

        fun previewData(): GiphyListUiModel {
            return GiphyListUiModel(
                gifs = emptyList()
            )
        }
    }
}