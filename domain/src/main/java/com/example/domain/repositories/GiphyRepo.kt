package com.example.domain.repositories

import com.example.domain.model.GifModel

interface GiphyRepo {

    suspend fun retrieveTrendingGifs(apiKey: String, limit: Int?, offset: Int?): List<GifModel>
}