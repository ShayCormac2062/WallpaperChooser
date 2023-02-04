package com.example.wallpaperchooser.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wallpaperchooser.ui.screen.category.CategoryScreen
import com.example.wallpaperchooser.ui.screen.wallpapers.WallpapersScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        count = 2
    ) { index ->
        val viewModel: MainViewModel = hiltViewModel()
        if (index == 0) {
            CategoryScreen(
                pagerState,
                viewModel,
            )
        } else WallpapersScreen(
            pagerState,
            viewModel
        )
    }
}
