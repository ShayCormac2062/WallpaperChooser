package com.example.wallpaperchooser.domain.repository

import com.example.wallpaperchooser.domain.model.Wallpaper
import com.example.wallpaperchooser.utils.RequestResult

interface WallpaperRepository {

    suspend fun getWallpapers(
        category: String,
        page: Int,
    ): RequestResult<List<Wallpaper>?>

}
