package com.example.wallpaperchooser.data.network.api

import com.example.wallpaperchooser.data.network.dto.WallpaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperApi {

    @GET("?key=33106230-b104905cd7ff74ed17e2229af")
    suspend fun getWallpapers(
        @Query("category") category: String,
        @Query("page") page: Int,
    ) : Response<WallpaperResponse>

}
