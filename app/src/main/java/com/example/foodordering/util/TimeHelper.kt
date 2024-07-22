package com.example.foodordering.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object TimeHelper {
    @SuppressLint("SimpleDateFormat")
    fun convertToDateTime(time: Long): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(time))
    }
}