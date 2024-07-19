package com.example.foodordering.util

object AuthHelper {

    fun validateEmail(email: String): Boolean {
        return email.matches(Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
    }

    fun validatePassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$"))
    }

    fun validateUsername(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z ]+$"))
    }

    fun validatePhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{10}$"))
    }

    private const val MOD: Long = (1e9 + 7).toLong()
    private const val BASE: Long = 311

    private fun hashFunction(s: String): Long {
        var res: Long = 0
        for (c in s)
            res = (res * BASE + c.code - 'a'.code + 1) % MOD
        return res
    }

    fun getHashUsername(username: String): Long {
        return hashFunction(username)
    }

    fun getHashPassword(password: String): Long {
        return hashFunction(password)
    }
}