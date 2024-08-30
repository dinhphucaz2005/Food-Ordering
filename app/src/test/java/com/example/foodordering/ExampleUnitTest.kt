package com.example.foodordering

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        fun Int.toVND(): String {
            val formatter = java.text.DecimalFormat("#,###")
            return formatter.format(this) + " VND"
        }
        println(5000000.toVND())
    }
}