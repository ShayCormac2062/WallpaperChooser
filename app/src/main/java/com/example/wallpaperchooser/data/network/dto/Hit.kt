package com.example.wallpaperchooser.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hit(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    @SerialName("user_id")
    val userId: Int,
    val views: Int,
    @SerialName("webformatHeight")
    val webFormatHeight: Int,
    @SerialName("webformatURL")
    val webFormatURL: String,
    @SerialName("webformatWidth")
    val webFormatWidth: Int
)
