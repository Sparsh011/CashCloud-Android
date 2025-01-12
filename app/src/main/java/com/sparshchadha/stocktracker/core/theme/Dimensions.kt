package com.sparshchadha.stocktracker.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.R

object Dimensions {

    @Composable
    fun statusBarPadding(): Dp {
        return dimensionResource(id = R.dimen.status_bar_padding)
    }

    @Composable
    fun smallPadding(): Dp {
        return dimensionResource(id = R.dimen.small_padding)
    }

    @Composable
    fun extraSmallPadding(): Dp {
        return dimensionResource(id = R.dimen.extra_small_padding)
    }

    @Composable
    fun mediumPadding(): Dp {
        return dimensionResource(id = R.dimen.medium_padding)
    }

    @Composable
    fun largePadding(): Dp {
        return dimensionResource(id = R.dimen.large_padding)
    }

    @Composable
    fun extraLargePadding(): Dp {
        return dimensionResource(id = R.dimen.extra_large_padding)
    }

    @Composable
    fun largeIconSize(): Dp {
        return dimensionResource(id = R.dimen.large_icon_size)
    }

    @Composable
    fun topBarHeight(): Dp {
        return dimensionResource(id = R.dimen.top_bar_height)
    }

    @Composable
    fun profilePicSize(): Dp {
        return dimensionResource(id = R.dimen.profile_pic_size)
    }

    @Composable
    fun cornerRadius(): Dp {
        return dimensionResource(id = R.dimen.corner_radius)
    }

    @Composable
    fun shimmerChartHeight(): Dp {
        return dimensionResource(id = R.dimen.shimmer_chart_height)
    }
}

object FontSizes {

    @Composable
    fun extraSmallFontSize(): Dp {
        return dimensionResource(id = R.dimen.small_text_size)
    }

    @Composable
    fun smallFontSize(): Dp {
        return dimensionResource(id = R.dimen.small_text_size)
    }

    @Composable
    fun mediumFontSize(): Dp {
        return dimensionResource(id = R.dimen.medium_text_size)
    }

    @Composable
    fun largeFontSize(): Dp {
        return dimensionResource(id = R.dimen.large_text_size)
    }

    @Composable
    fun extraLargeFontSize(): Dp {
        return dimensionResource(id = R.dimen.extra_large_text_size)
    }

    private val Int.nonScaledSp
        @Composable
        get() = (this / LocalDensity.current.fontScale).sp

    @Composable
    fun smallNonScaledFontSize(): TextUnit {
        return 12.nonScaledSp
    }

    @Composable
    fun extraSmallNonScaledFontSize(): TextUnit {
        return 8.nonScaledSp
    }

    @Composable
    fun mediumNonScaledFontSize(): TextUnit {
        return 16.nonScaledSp
    }


    @Composable
    fun largeNonScaledFontSize(): TextUnit {
        return 20.nonScaledSp
    }


    @Composable
    fun extraLargeNonScaledFontSize(): TextUnit {
        return 24.nonScaledSp
    }
}