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
    val password = mutableStateOf("00000000")
    val phoneNumber = mutableStateOf("0987654321")
    val email = mutableStateOf("dinhphucaz52@gmail.com")

//    val username = mutableStateOf("")
//    val password = mutableStateOf("")
//    val phoneNumber = mutableStateOf("")
//    val email = mutableStateOf("")

    val registerSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    var registerMessage = mutableStateOf("")

    fun register() {
        viewModelScope.launch {

            if (
                AuthHelper.isInvalidEmail(email.value) &&
                AuthHelper.isInvalidPassword(password.value) &&
                AuthHelper.isInvalidPhoneNumber(phoneNumber.value) &&
                AuthHelper.isInvalidUsername(username.value)
            ) {
                isLoading.value = false
                registerMessage.value = "Invalid input"
                return@launch
            }

            isLoading.value = true

            val result =
                repository.register(username.value, email.value, password.value, phoneNumber.value)

            when (result) {
                is AppResource.Success -> {
                    registerSuccess.value = true
                    registerMessage.value = "Register success"
                }

                is AppResource.Error -> {
                    registerMessage.value = result.error
                }
            }
            isLoading.value = false
        }

    }
}