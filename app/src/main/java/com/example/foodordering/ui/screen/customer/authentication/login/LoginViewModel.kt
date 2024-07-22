package com.example.foodordering.ui.screen.customer.authentication.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.di.UserTemp
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.AuthHelper
import kotlinx.coroutines.launch

//@HiltViewModel
class LoginViewModel
//@Inject constructor(private val repository: AuthRepository, )
    : ViewModel() {

    private val repository = AppModule.provideAuthRepository()

    var email = mutableStateOf("dinhphucaz52@gmail.com")
    var password = mutableStateOf("00000000")
    var isLoginLoading = mutableStateOf(false)
    var loginSuccess = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun login() {
        viewModelScope.launch {
            if (AuthHelper.isInvalidEmail(email.value) &&
                AuthHelper.isInvalidPassword(password.value)
            )
                return@launch
            if (isLoginLoading.value)
                return@launch
            isLoginLoading.value = true
            when (val result = repository.login(email.value, password.value)) {
                is AppResource.Success -> {
                    val userDTO = result.data
                    if (userDTO != null) {
                        UserTemp.id = userDTO.id
                        loginSuccess.value = true
                    }
                }

                is AppResource.Error -> {
                    errorMessage.value = result.error
                }
            }
            isLoginLoading.value = false
        }
    }
}