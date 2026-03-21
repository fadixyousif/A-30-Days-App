package com.fadi.a30daysapp.data

import com.fadi.a30daysapp.R
import com.fadi.a30daysapp.model.Day

object DaysData {
    val days = listOf(
        Day(dayNumber = 1, titleRes = R.string.day1_title, descriptionRes = R.string.day1_desc, imageRes = R.drawable.ic_launcher_background), // placeholder text, description and image until finding one
        Day(dayNumber = 2, titleRes = R.string.day2_title, descriptionRes = R.string.day2_desc, imageRes = R.drawable.ic_launcher_background), // placeholder text, description and image until finding one
    )
}
