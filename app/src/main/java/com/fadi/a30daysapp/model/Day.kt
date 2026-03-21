package com.fadi.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    val dayNumber: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
