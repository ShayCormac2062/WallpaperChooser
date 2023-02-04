package com.example.wallpaperchooser.ui.screen.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpaperchooser.R
import com.example.wallpaperchooser.ui.screen.main.MainViewModel
import com.example.wallpaperchooser.utils.AppText
import com.example.wallpaperchooser.utils.appGradient
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategoryScreen(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()
        val categories = listOf(
            "Backgrounds",
            "Fashion",
            "Nature",
            "Animals",
            "Computer",
            "Music",
            "Food",
            "Sports"
        )
        AppText(
            text = stringResource(R.string.categories),
            size = 30.sp,
            modifier = Modifier
                .padding(24.dp)
        )
        LazyColumn {
            items(
                categories.size
            ) { index ->
                Category(categories[index]) {
                    coroutineScope.launch {
                        viewModel.getWallpapers(it.lowercase())
                        pagerState.animateScrollToPage(1)
                    }
                }
            }
        }
    }
}

@Composable
fun Category(
    category: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .clip(RoundedCornerShape(30.dp))
            .clickable { onClick.invoke(category) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    appGradient()
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                modifier = Modifier.padding(start = 24.dp),
                text = category,
                size = 25.sp
            )
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    }
}
