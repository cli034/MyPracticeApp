package com.example.domain.model

data class GifModel(
    val type: String,
    val id: String,
    val slug: String,
    val url: String,
    val bitly_url: String,
    val embed_url: String,
    val source: String,
    val rating: String,
    val images: ImageModel,
    val title: String
)