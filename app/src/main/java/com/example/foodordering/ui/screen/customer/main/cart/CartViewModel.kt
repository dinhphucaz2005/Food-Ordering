package com.example.foodordering.ui.screen.customer.main.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
class CartViewModel @Inject constructor(
    private val repository: CustomerRepository,
) : ViewModel() {


    private val _isUpdatedCart = MutableStateFlow(false)
    private val _cart = MutableStateFlow<Cart?>(null)
    private val _inProgress = MutableStateFlow(false)

    val cart: StateFlow<Cart?> = _cart.asStateFlow()
    val isUpdatedCart: StateFlow<Boolean> = _isUpdatedCart.asStateFlow()
    val inProgress: StateFlow<Boolean> = _inProgress.asStateFlow()


    init {
        viewModelScope.launch {
            when (val result = repository.getCart()) {
                is AppResource.Success -> {
                    _cart.value = result.data?.let { Cart(it) }
                    println(_cart.value)
                }

                else -> {
                    TODO("Handle error")
                }
            }
        }
    }

    fun addCart(foodId: String) {
        val cart = _cart.value?.copy() ?: return
        cart.addFood(foodId)
        _cart.value = cart
    }

    fun removeCart(foodId: String) {
        val cart = _cart.value?.copy() ?: return
        cart.removeFood(foodId)
        _cart.value = cart
    }

    fun updateCart() {
        viewModelScope.launch {
            _cart.value?.let { cart ->
                cart.foods.forEach { food ->
                    when (val result = repository.updateCart(food.id, cart.id, food.quantity)) {
                        is AppResource.Success -> {

                        }

                        is AppResource.Error -> {
                            println(result.error)
                        }
                    }
                }
            }
            _isUpdatedCart.value = true
        }
    }


}