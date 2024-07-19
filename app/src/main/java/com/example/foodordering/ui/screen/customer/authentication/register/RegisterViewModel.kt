package com.example.foodordering.ui.screen.customer.authentication.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.AuthHelper
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository = AppModule.provideAuthRepository()

    val username = mutableStateOf("dinhphucaz52")
    val password = mutableStateOf("dinhphucaz52")
    val phoneNumber = mutableStateOf("0369522657")
    val email = mutableStateOf("dinhphucaz52@gmail.com")
    val isLoading = mutableStateOf(false)

    private var registerMessage: String = ""

    fun getRegisterMessage(): String = registerMessage

    fun register() {
        viewModelScope.launch {

            if (
                AuthHelper.validateEmail(email.value) &&
                AuthHelper.validatePassword(password.value) &&
                AuthHelper.validatePhoneNumber(phoneNumber.value) &&
                AuthHelper.validateUsername(username.value)
            )
                return@launch
            isLoading.value = true
            val result =
                repository.register(
                    username.value,
                    "username",
                    email.value,
                    password.value,
                    phoneNumber.value
                )
            isLoading.value = false
            registerMessage = when (result) {
                is AppResource.Success -> {
                    "Register success"
                }

                is AppResource.Error -> {
                    result.error
                }
            }
        }
        // Handle success case
    }
}