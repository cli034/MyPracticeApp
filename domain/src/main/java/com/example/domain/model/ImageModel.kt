package com.example.domain.model

data class ImageModel(
    val original: ImageDetailModel,
    val fixed_height_downsampled: ImageDetailModel
)
