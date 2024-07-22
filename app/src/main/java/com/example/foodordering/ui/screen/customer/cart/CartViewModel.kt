package com.example.foodordering.ui.screen.customer.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.di.UserTemp
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.CartItem
import com.example.foodordering.domain.model.CartItemDTO
import com.example.foodordering.domain.model.Food
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartViewModel(
    private val database: DatabaseReference = AppModule.provideDatabase()
) : ViewModel() {

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

    val inProgress = mutableStateOf(false)

    fun checkout() {
        viewModelScope.launch {
            inProgress.value = true
            delay(2000)
            val newBill = Bill(
                id = "${System.currentTimeMillis()}",
                userId = UserTemp.id,
                time = System.currentTimeMillis(),
                total = cart.sumOf { it.food.price * it.quantity },
                cart = cart.map {
                    CartItemDTO(it.food.id, it.quantity)
                }
            )
            database.child("bill").child("${System.currentTimeMillis()}")
                .setValue(newBill)
                .addOnSuccessListener {
                    cart.clear()
                }
                .addOnFailureListener {
                    println("CartViewModel.checkout: ${it.message}")
                }
            inProgress.value = false
        }
    }
}