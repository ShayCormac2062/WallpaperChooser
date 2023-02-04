package com.example.wallpaperchooser.ui.screen.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wallpaperchooser.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(controller: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_wallpaper),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.White else Color.Black)
        )
        val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = InfiniteRepeatableSpec(
                animation = tween(1000, easing = FastOutSlowInEasing)
            )
        )
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 24.dp)
                .rotate(angle),
            progress = 0.5f,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            strokeWidth = 2.dp,
        )
        LaunchedEffect(key1 = true) {
            delay(500)
            controller.navigate("main_screen")
        }
    }
}
