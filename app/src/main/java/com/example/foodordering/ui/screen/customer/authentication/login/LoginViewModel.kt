package com.example.foodordering.ui.screen.customer.authentication.login

import RetrofitClient
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.AuthHelper
import kotlinx.coroutines.launch

//@HiltViewModel
class LoginViewModel
//@Inject constructor(private val repository: AuthRepository, )
    : ViewModel() {

    private val repository = AppModule.provideAuthRepository()

    var username = mutableStateOf("dinh.phuc.17.5.25@gmail.com")
    var password = mutableStateOf("00000000")
    var isLoginLoading = mutableStateOf(false)
    var loginSuccess = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun login() {
        viewModelScope.launch {
            if (AuthHelper.validateEmail(username.value) && AuthHelper.validatePassword(password.value)) {
                return@launch
            }
            if (isLoginLoading.value) {
                return@launch
            }
            val result = repository.login(username.value, password.value)
            if (result is AppResource.Success) {
                val userDTO = result.data
                if (userDTO != null) {
                    userDTO.token?.let { RetrofitClient.createRetrofitWithToken(it) }
                }
                loginSuccess.value = true
            } else {
                errorMessage.value = (result as AppResource.Error).error
            }
        }
    }
}