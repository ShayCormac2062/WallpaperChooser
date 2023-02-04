package com.example.wallpaperchooser.di

import com.example.wallpaperchooser.data.network.api.WallpaperApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetModule {


    companion object {
        private const val WALLPAPER_ROOT = "https://pixabay.com/api/"
        private const val READ_TIME_OUT = 1L
        private const val CONNECT_TIME_OUT = 1L
    }

    @Provides
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .cache(null)
            .build()

    @Provides
    fun provideJsonConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
    }

    @Provides
    fun provideRetrofit(
        okHttp: OkHttpClient,
        jsonConverterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttp)
            .baseUrl(WALLPAPER_ROOT)
            .addConverterFactory(jsonConverterFactory)
            .build()

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): WallpaperApi =
        retrofit.create(WallpaperApi::class.java)
}
