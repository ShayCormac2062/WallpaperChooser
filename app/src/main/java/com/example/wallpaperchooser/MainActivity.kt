package com.example.wallpaperchooser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpaperchooser.ui.screen.main.MainScreen
import com.example.wallpaperchooser.ui.screen.splash.SplashScreen
import com.example.wallpaperchooser.ui.theme.WallpaperChooserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            WallpaperChooserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupNavGraph()
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "splash_screen") {
        composable(route = "splash_screen") {
            SplashScreen(controller)
        }
        composable(route = "main_screen") {
            MainScreen()
        }
    }
}
