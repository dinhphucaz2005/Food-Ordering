package com.example.foodordering.ui.screen.customer.cart

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.foodordering.domain.model.CartItem
import com.example.foodordering.domain.model.Food

class CartViewModel : ViewModel() {

    val cart = mutableStateListOf<CartItem>(
        CartItem(),
        CartItem(),
        CartItem(),
        CartItem(),
    )

    var totalPrice = mutableLongStateOf(0)


    fun addToCart(index: Int) {
        cart[index] = cart[index].copy(quantity = cart[index].quantity + 1)
        totalPrice.longValue += cart[index].food.price
    }

    fun removeFromCart(index: Int) {
        cart[index] = cart[index].copy(quantity = cart[index].quantity - 1)
        totalPrice.longValue -= cart[index].food.price
    }


}