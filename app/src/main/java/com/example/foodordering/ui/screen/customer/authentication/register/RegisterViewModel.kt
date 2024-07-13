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

    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val email = mutableStateOf("")
    val registerSuccess = mutableStateOf(false)

    private var errorMessage: String = ""

    fun getErrorMessage(): String {
        return errorMessage
    }

    fun register() {
        viewModelScope.launch {

            if (
                AuthHelper.validateEmail(email.value) &&
                AuthHelper.validatePassword(password.value) &&
                AuthHelper.validatePhoneNumber(phoneNumber.value) &&
                AuthHelper.validateUsername(username.value)
            )
                return@launch

            val result =
                repository.register(username.value, email.value, password.value, phoneNumber.value)

            if (result is AppResource.Success) {
                registerSuccess.value = true
            } else {
                errorMessage = (result as AppResource.Error).error
            }

        }
    }

}