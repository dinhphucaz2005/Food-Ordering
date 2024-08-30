package com.example.foodordering.util

object ValidationHelper {

    fun isInvalidEmail(email: String): Boolean {
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$""")
        return !emailRegex.matches(email)
    }

    fun isInvalidPassword(password: String): Boolean {
        return password.length < 8
    }

    fun isInvalidPhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberRegex = Regex("""^\d{10}$""")
        return !phoneNumberRegex.matches(phoneNumber)
    }


}