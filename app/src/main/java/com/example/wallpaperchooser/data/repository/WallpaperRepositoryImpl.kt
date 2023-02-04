package com.example.wallpaperchooser.data.repository

import com.example.wallpaperchooser.data.network.api.WallpaperApi
import com.example.wallpaperchooser.data.network.mapper.ResponseMapper.toWallpaper
import com.example.wallpaperchooser.domain.model.Wallpaper
import com.example.wallpaperchooser.domain.repository.WallpaperRepository
import com.example.wallpaperchooser.utils.ApplicationException
import com.example.wallpaperchooser.utils.RequestResult
import java.net.SocketException
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val api: WallpaperApi
) : WallpaperRepository {

    override suspend fun getWallpapers(
        category: String,
        page: Int
    ): RequestResult<List<Wallpaper>?> = try {
        val result = api.getWallpapers(category, page)
        with(result) {
            if (isSuccessful && body() != null) {
                RequestResult.Success(
                    data = body()?.hits?.map { it.toWallpaper() }
                )
            } else {
                RequestResult.Error(
                    message = ApplicationException.ApiException()
                )
            }
        }
    } catch (ex: Throwable) {
        RequestResult.Error(
            message = when(ex) {
                is SocketException -> ApplicationException.VPNException()
                else -> ApplicationException.InternetException()
            }
        )

    }

}
