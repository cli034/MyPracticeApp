package com.example.data.models

import com.example.domain.model.GifModel
import com.example.domain.model.MetaModel
import com.example.domain.model.PaginationModel


data class NetworkGiphyResponse(
    val data: List<GifModel>,
    val pagination: PaginationModel,
    val meta: MetaModel
)