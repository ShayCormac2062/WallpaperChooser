package com.example.wallpaperchooser.data.network.mapper

import com.example.wallpaperchooser.data.network.dto.Hit
import com.example.wallpaperchooser.domain.model.Wallpaper

object ResponseMapper {

    fun Hit.toWallpaper() = Wallpaper(
        largeImageURL = largeImageURL
    )
}
