package com.example.foodordering.ui.screen.customer.authentication.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val name = mutableStateOf("dinhphucaz52")
    val password = mutableStateOf("00000000")
    val phoneNumber = mutableStateOf("0987654321")
    val email = mutableStateOf("dinhphucaz52@gmail.com")
    val address = mutableStateOf("Ho Chi Minh City")


    val registerSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    var registerMessage = mutableStateOf("")
    fun register() {
        viewModelScope.launch {

            if (
                AuthHelper.isInvalidEmail(email.value) &&
                AuthHelper.isInvalidPassword(password.value) &&
                AuthHelper.isInvalidPhoneNumber(phoneNumber.value) &&
                AuthHelper.isInvalidUsername(name.value)
            ) {
                isLoading.value = false
                registerMessage.value = "Invalid input"
                return@launch
            }

            isLoading.value = true

            val result =
                repository.register(
                    name = name.value,
                    password = password.value,
                    phoneNumber = phoneNumber.value,
                    email = email.value,
                )

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