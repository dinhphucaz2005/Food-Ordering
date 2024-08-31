package com.example.foodordering.ui.screen.customer.main.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.model.Cart
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CustomerRepository,
) : ViewModel() {

    companion object {
        const val TAG = "CartViewModel"
    }

    private val _cart = MutableStateFlow<Cart?>(null)
    private val _isLoading = MutableStateFlow(false)

    val cart: StateFlow<Cart?> = _cart.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    init {
        viewModelScope.launch {
            when (val result = repository.getCart()) {
                is AppResource.Success -> {
                    _cart.value = result.data?.let { Cart(it) }
                }

                is AppResource.Error -> {
//                    TODO("Handle error")
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

    fun updateCart(callback: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _cart.value?.let { cart ->
                cart.foods.forEach { food ->
                    when (val result = repository.updateCart(food.id, cart.id, food.quantity)) {
                        is AppResource.Success -> {
//                            TODO("Handle success")
                        }

                        is AppResource.Error -> {
                            Log.d(TAG, result.error)
                        }
                    }
                }
            }
            _isLoading.value = false
            callback()
        }
    }


}