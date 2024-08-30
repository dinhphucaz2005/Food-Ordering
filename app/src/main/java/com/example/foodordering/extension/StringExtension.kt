package com.example.foodordering.extension

fun Int?.toVND(): String {
    if (this == null)
        return "0 VND"
    val formatter = java.text.DecimalFormat("#,###")
    return formatter.format(this) + " VND"
}