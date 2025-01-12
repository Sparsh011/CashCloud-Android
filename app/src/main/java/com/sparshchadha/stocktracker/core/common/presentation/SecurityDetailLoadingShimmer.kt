package com.sparshchadha.stocktracker.core.common.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.sparshchadha.stocktracker.core.theme.Dimensions

@Composable
fun SecurityDetailLoadingShimmer() {
    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.3f),
        Color.Gray.copy(alpha = 0.7f),
        Color.Gray.copy(alpha = 0.3f),
    )

    val transition = rememberInfiniteTransition(label = "Shimmer Animation")

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Shimmer Translate Animation",
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation.value, translateAnimation.value),
        end = Offset(translateAnimation.value + 200f, translateAnimation.value + 200f),
    )

    Pulsating {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimensions.smallPadding())
        ) {
            // Placeholder for stock name and price
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.mediumPadding())
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(Dimensions.mediumPadding()))
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(20.dp)
                            .clip(RoundedCornerShape(Dimensions.cornerRadius()))
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(16.dp)
                            .clip(RoundedCornerShape(Dimensions.cornerRadius()))
                            .background(brush)
                    )
                }
            }

            Spacer(Modifier.height(Dimensions.largePadding()))

            // Placeholder for stock chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.shimmerChartHeight())
                    .clip(RoundedCornerShape(Dimensions.cornerRadius()))
                    .background(brush)
            )

            Spacer(Modifier.height(Dimensions.largePadding()))

            // Placeholder for additional details (e.g., stats)
            repeat(3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimensions.smallPadding())
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(Dimensions.mediumPadding()))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(Dimensions.cornerRadius()))
                            .background(brush)
                    )
                }
            }
        }
    }
}



/**
 * Use this to add a pulsating effect to a composable.
 * We are using [Modifier.graphicsLayer] instead of [Modifier.scale] because the latter triggers recomposition every time the composable shrinks or expands,
 * which leads to more than 60 recompositions per second.
 * @param pulseFraction A floating number indicating by how much the composable should scale (either shrink or expand)
 * @param content A composable lambda that will be used to display the content you wish to have a pulsating effect.
 */
@Composable
fun Pulsating(pulseFraction: Float = 1.015f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // if pulseFraction increases, the size of composable during animation will increase. if pulseFraction < initialValue, then the size of composable during animation decreases
    val animationScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(modifier = Modifier.graphicsLayer {
        scaleX = animationScale
        scaleY = animationScale
    }) {
        content()
    }
}