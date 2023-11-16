package com.example.data.service

import com.example.data.models.NetworkGiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("gifs/trending")
    suspend fun retrieveTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): NetworkGiphyResponse
}