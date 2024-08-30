package com.example.foodordering.provider

import android.content.Context
import com.example.foodordering.util.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProvider @Inject constructor(
    context: Context,
) {
    private val sharedPreferences =
        context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveUser(email: String, password: String, token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("auth_token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", "")
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", "")
    }
}