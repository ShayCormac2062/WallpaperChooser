package com.example.wallpaperchooser.utils

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpaperchooser.R
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    size: TextUnit? = null,
    color: Color? = null
) {
    Text(
        modifier = modifier,
        color = color ?: if (isSystemInDarkTheme()) Color.White else Color.Black,
        text = text,
        fontFamily = FontFamily(Font(R.font.coolvetica_rg)),
        fontSize = size ?: 16.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 7
    )
}
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun ErrorMessage(
    message: String,
    onClick: () -> Unit
) {
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            colorFilter = ColorFilter.tint(color)
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 24.dp),
            text = message,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        AppButton(
            modifier = Modifier
                .width(160.dp)
                .height(40.dp)
                .background(appGradient())
                .clip(RoundedCornerShape(50.dp)),
            onClick = { onClick.invoke() },
            text = stringResource(R.string.try_again),
            textColor = color
        )
    }
}

@Composable
fun ProgressBar() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1500, easing = FastOutSlowInEasing)
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.rotate(angle),
            progress = 0.5f,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            strokeWidth = 2.dp,
        )
        AppText(
            text = stringResource(R.string.loading))
    }
}

@Composable
fun EmptyListMessage() {
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_wallpaper),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            colorFilter = ColorFilter.tint(color),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.no_applications),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyListMessagePreview() {
    EmptyListMessage()
}

@Preview(showBackground = true)
@Composable
fun ProgressBarPreview() {
    ProgressBar()
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color? = null,
    onClick: () -> Unit
) {
    Button(
        contentPadding = PaddingValues(),
        onClick = { onClick.invoke() })
    {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            AppText(text = text, color = textColor)
        }
    }
}

@Composable
fun appGradient() = Brush.linearGradient(
    listOf(
        if (isSystemInDarkTheme()) Color.Black else Color.White,
        Color.LightGray
    )
)
