package com.example.wallpaperchooser.ui.screen.main

import com.example.wallpaperchooser.domain.model.Wallpaper
import com.example.wallpaperchooser.utils.ApplicationException

sealed class WallpapersEvent {

    object WallpapersEmptyEvent : WallpapersEvent()

    object WallpapersLoadingEvent : WallpapersEvent()

    data class WallpapersErrorEvent(
        val exception: ApplicationException?
    ) : WallpapersEvent()

    data class WallpapersSuccessEvent(
        val wallpaper: List<Wallpaper>?
    ) : WallpapersEvent()

}
