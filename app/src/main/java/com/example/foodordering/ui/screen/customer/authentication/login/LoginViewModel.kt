package com.example.foodordering.ui.screen.customer.authentication.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoginSuccess = MutableStateFlow(false)
    private val _isLoading = MutableStateFlow(false)

    val isLoginSuccess = _isLoginSuccess.asStateFlow()
    val isLoading = _isLoading.asStateFlow()
    var errorMessage = mutableStateOf("")

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (AuthHelper.isInvalidEmail(email) &&
                AuthHelper.isInvalidPassword(password)
            )
                return@launch
            if (_isLoading.value)
                return@launch
            _isLoading.value = true
            when (val result = repository.login(email, password)) {
                is AppResource.Success -> {
                    val userDTO = result.data
                    if (userDTO != null) {
                        _isLoginSuccess.value = true
                    }
                }

                is AppResource.Error -> {
                    errorMessage.value = result.error
                }
            }
            _isLoading.value = false
        }
    }
}