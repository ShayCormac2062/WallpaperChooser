package com.example.wallpaperchooser.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class WallpaperResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
