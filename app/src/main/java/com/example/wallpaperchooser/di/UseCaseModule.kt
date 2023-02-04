package com.example.wallpaperchooser.di

import com.example.wallpaperchooser.domain.repository.WallpaperRepository
import com.example.wallpaperchooser.domain.usecase.GetWallpapersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun bindGetWallpapersUseCase(
        repository: WallpaperRepository,
        @Named("io") dispatcher: CoroutineDispatcher
    ): GetWallpapersUseCase = GetWallpapersUseCase(repository, dispatcher)

}
