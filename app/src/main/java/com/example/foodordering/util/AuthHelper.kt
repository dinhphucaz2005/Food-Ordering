package com.example.foodordering.util

object AuthHelper {


    fun isInvalidEmail(email: String): Boolean {
        return email.isEmpty() || !email.matches(Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
    }

    fun isInvalidPassword(password: String): Boolean {
        return password.isEmpty() || !password.matches(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$"))
    }

    fun isInvalidUsername(name: String): Boolean {
        return name.isEmpty() || !name.matches(Regex("^[a-zA-Z ]+$"))
    }

    fun isInvalidPhoneNumber(phone: String): Boolean {
        return phone.isEmpty() || !phone.matches(Regex("^\\d{10}$"))
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