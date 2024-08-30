package com.example.foodordering.ui.screen.customer.main.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Cart
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {
    fun confirm() {
        TODO("Implement confirm")
    }

    private val _cart = MutableStateFlow<Cart?>(null)

    val cart: StateFlow<Cart?> = _cart.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = repository.getCart()) {
                is AppResource.Success -> {
                    _cart.value = result.data?.let { Cart(it) }
                    println(_cart.value)
                }

                is AppResource.Error -> {
                    println(result.error)
                }
            }
        }
    }

}