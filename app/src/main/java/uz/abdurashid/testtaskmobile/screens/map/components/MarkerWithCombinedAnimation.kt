package uz.abdurashid.testtaskmobile.screens.map.components

import android.view.animation.BounceInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AnimatedMarker(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    val scale = remember { Animatable(1f) }

    // Trigger the animation when `isSelected` changes
    LaunchedEffect(isSelected) {
        if (isSelected) {
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
            )
        } else {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
        }
    }

 /*   Box(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .size(50.dp)
            .background(Color.Red, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Marker",
            tint = Color.White
        )
    }*/

    Box(
        modifier = modifier
            .size(50.dp)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            ),
        contentAlignment = Alignment.Center
    ) {
        // Soya
        Box(
            modifier = Modifier
                .size(30.dp) // Soyaning o'lchami
                .scale(scale.value) // Soya animatsiyaga moslashadi
                .blur(15.dp) // Blur effekti
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Gray
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Marker
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Red, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Marker",
                tint = Color.White
            )
        }
    }
}


@Composable
fun BouncingMarker(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    val offsetY = remember { Animatable(0f) }

    // Trigger the animation when `isSelected` changes
    LaunchedEffect("isSelected") {
        if (isSelected) {
            offsetY.animateTo(
                targetValue = -30f,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            offsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 200, easing = BounceInterpolator() as Easing )
            )
        }
    }

    Box(
        modifier = modifier
            .offset { IntOffset(0, offsetY.value.toInt()) }
            .size(50.dp)
            .background(Color.Red, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Marker",
            tint = Color.White
        )
    }
}

fun BounceInterpolator.asEasing(): Easing = Easing { input ->
    getInterpolation(input)
}


@Composable
fun MarkerWithCombinedAnimation(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    val offsetY = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            // Sakrash va kattalashishni bir vaqtda boshlash
            launch {
                offsetY.animateTo(-30f, tween(300, easing = LinearOutSlowInEasing))
                offsetY.animateTo(0f, tween(200, easing = BounceInterpolator().asEasing()))
            }
            launch {
                scale.animateTo(1.2f, tween(300, easing = LinearOutSlowInEasing))
                scale.animateTo(1f, tween(200, easing = FastOutLinearInEasing))
            }
        }
    }

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            .offset { IntOffset(0, offsetY.value.toInt()) }
            .size(50.dp)
            .background(Color.Red, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Marker",
            tint = Color.White
        )
    }
}
