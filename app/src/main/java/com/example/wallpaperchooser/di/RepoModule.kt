package com.example.wallpaperchooser.di

import com.example.wallpaperchooser.data.network.api.WallpaperApi
import com.example.wallpaperchooser.data.repository.WallpaperRepositoryImpl
import com.example.wallpaperchooser.domain.repository.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun bindWallpaperRepository(
       api: WallpaperApi
    ): WallpaperRepository = WallpaperRepositoryImpl(api)

}
