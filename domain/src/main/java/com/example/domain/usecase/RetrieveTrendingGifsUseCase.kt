package com.example.domain.usecase

import com.example.domain.model.GifModel
import com.example.domain.repositories.GiphyRepo
import javax.inject.Inject

class RetrieveTrendingGifsUseCase @Inject constructor(
    private val giphyRepo: GiphyRepo
) {

    suspend operator fun invoke(
        apiKey: String,
        limit: Int? = null,
        offset: Int? = null
    ): List<GifModel> {
        return giphyRepo.retrieveTrendingGifs(
            apiKey = apiKey,
            limit = limit,
            offset = offset
        )
    }
}