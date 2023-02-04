package com.example.wallpaperchooser.ui.screen.wallpapers

import com.example.wallpaperchooser.utils.AppButton
import com.example.wallpaperchooser.utils.appGradient
import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun WallpaperBottomSheet(
    url: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Scaffold(
        content = {
            Image(
                painter = rememberAsyncImagePainter(model = url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(),
                contentScale = ContentScale.Crop
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                AppButton(
                    text = "Set Wallpaper",
                    modifier = Modifier
                        .width(120.dp)
                        .height(45.dp)
                        .background(appGradient()),
                ) {
                    scope.launch {
                        val loader = ImageLoader(context)
                        val request = ImageRequest.Builder(context)
                            .data(url)
                            .allowHardware(false) // Disable hardware bitmaps.
                            .build()
                        val result = (loader.execute(request) as SuccessResult).drawable
                        WallpaperManager.getInstance(context).setBitmap((result as BitmapDrawable).bitmap)
                    }
                }
            }
        }
    )
}
