package com.example.wallpaperchooser.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperchooser.domain.usecase.GetWallpapersUseCase
import com.example.wallpaperchooser.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetWallpapersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<WallpapersEvent> =
        MutableStateFlow(WallpapersEvent.WallpapersEmptyEvent)
    val uiState: StateFlow<WallpapersEvent> = _uiState

    private var currentCategory: String? = null

    fun getWallpapers(
        category: String? = null
    ) {
        _uiState.value = WallpapersEvent.WallpapersLoadingEvent
        currentCategory = category ?: currentCategory
        viewModelScope.launch {
            val result = useCase(
                currentCategory.toString(),
                (1..10).random()
            )
            _uiState.value = when (result) {
                is RequestResult.Success -> {
                    WallpapersEvent.WallpapersSuccessEvent(result.data)
                }
                else -> {
                    WallpapersEvent.WallpapersErrorEvent(
                        exception = result.message
                    )
                }
            }
        }
    }

}
