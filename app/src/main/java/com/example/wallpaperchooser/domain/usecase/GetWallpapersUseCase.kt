package com.example.wallpaperchooser.domain.usecase

import com.example.wallpaperchooser.domain.model.Wallpaper
import com.example.wallpaperchooser.domain.repository.WallpaperRepository
import com.example.wallpaperchooser.utils.RequestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetWallpapersUseCase @Inject constructor(
    private val repository: WallpaperRepository,
    @Named("io") private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(
        category: String,
        page: Int
    ): RequestResult<List<Wallpaper>?> =
        withContext(dispatcher) {
            repository.getWallpapers(category, page)
        }

}
