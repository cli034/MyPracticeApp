package com.example.data.repositories

import com.example.data.service.GiphyService
import com.example.domain.model.GifModel
import com.example.domain.repositories.GiphyRepo
import javax.inject.Inject

class GiphyRepoImpl @Inject constructor(
    private val giphyService: GiphyService
) : GiphyRepo {

    override suspend fun retrieveTrendingGifs(apiKey: String, limit: Int?, offset: Int?): List<GifModel> {
        return giphyService.retrieveTrendingGifs(apiKey = apiKey, limit = limit, offset = offset).data
    }
}