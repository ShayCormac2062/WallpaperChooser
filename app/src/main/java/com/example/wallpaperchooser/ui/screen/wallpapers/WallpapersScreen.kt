package com.example.wallpaperchooser.ui.screen.wallpapers

import com.example.wallpaperchooser.utils.AppText
import com.example.wallpaperchooser.utils.EmptyListMessage
import com.example.wallpaperchooser.utils.ErrorMessage
import com.example.wallpaperchooser.utils.ProgressBar
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.wallpaperchooser.R
import com.example.wallpaperchooser.domain.model.Wallpaper
import com.example.wallpaperchooser.ui.screen.main.MainViewModel
import com.example.wallpaperchooser.ui.screen.main.WallpapersEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WallpapersScreen(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    val state by viewModel.uiState.collectAsState()
    when(state) {
        is WallpapersEvent.WallpapersSuccessEvent -> {
            WallpapersContent(
                pagerState,
                (state as WallpapersEvent.WallpapersSuccessEvent).wallpaper
            )
        }
        is WallpapersEvent.WallpapersEmptyEvent -> EmptyListMessage()
        is WallpapersEvent.WallpapersErrorEvent -> ErrorMessage(
            message = (state as WallpapersEvent.WallpapersErrorEvent)
                .exception
                ?.message
                .toString()
        ) {
            viewModel.getWallpapers()
        }
        else -> ProgressBar()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class)
@Composable
fun WallpapersContent(
    pagerState: PagerState,
    data: List<Wallpaper>?
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    var url by remember { mutableStateOf("") }
    ModalBottomSheetLayout(
        sheetContent = {
            WallpaperBottomSheet(url)
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val scope = rememberCoroutineScope()
            BackHandler(true) {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
            Header {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
            WallpapersGrid(
                data
            ) {
                scope.launch {
                    url = it
                    modalBottomSheetState.animateTo(
                        ModalBottomSheetValue.Expanded
                    )
                }
            }
        }
    }
}

@Composable
fun WallpapersGrid(
    wallpapers: List<Wallpaper>?,
    onClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(wallpapers?.size ?: 0) { index ->
                Wallpaper(
                    wallpapers?.get(index)?.largeImageURL.toString(),
                    onClick = onClick
                )
            }
        },
        contentPadding = PaddingValues(
            horizontal = 5.dp,
            vertical = 5.dp
        ),
        horizontalArrangement = Arrangement.SpaceEvenly
    )
}

@Composable
fun Header(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick.invoke() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.White else Color.Black)
            )
        }
        AppText(
            text = stringResource(R.string.wallpapers),
            size = 30.sp,
            modifier = Modifier
                .padding(24.dp),
        )
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.Black else Color.White)
            )
        }
    }
}

@Composable
fun Wallpaper(
    url: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(270.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable {
                onClick.invoke(url)
            },
        elevation = 16.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
