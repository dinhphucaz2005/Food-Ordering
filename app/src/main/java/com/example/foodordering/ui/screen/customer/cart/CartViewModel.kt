package com.example.foodordering.ui.screen.customer.cart

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.foodordering.domain.model.CartItem
import com.example.foodordering.domain.model.Food
import okhttp3.internal.concurrent.formatDuration

class CartViewModel : ViewModel() {

//    val cart = mutableStateListOf<CartItem>(
//        CartItem(),
//        CartItem(),
//        CartItem(),
//        CartItem(),
//    )
    val cart = mutableStateListOf<CartItem>()

    fun addToCart(index: Int) {
        cart[index] = cart[index].copy(quantity = cart[index].quantity + 1)
    }

    fun removeFromCart(index: Int) {
        cart[index] = cart[index].copy(quantity = cart[index].quantity - 1)
    }

    fun addToCart(food: Food) {
        cart.indices.forEach { index ->
            if (cart[index].food.id == food.id) {
                addToCart(index)
                return
            }
        }
        cart.add(CartItem(food, 1))
    }

}