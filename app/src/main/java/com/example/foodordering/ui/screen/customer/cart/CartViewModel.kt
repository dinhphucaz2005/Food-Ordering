package com.example.foodordering.ui.screen.customer.cart

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.CartItem
import com.example.foodordering.domain.model.Food
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.concurrent.formatDuration

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
            data class FirebaseCartItem(
                val idProduct: String,
                val quantity: Int
            )

            data class Bill(
                val total: Int,
                val time: String,
                var cart: List<FirebaseCartItem>
            )

            val newBill = Bill(
                cart.sumOf { it.food.price * it.quantity },
                "${System.currentTimeMillis()}",
                cart.map { FirebaseCartItem(it.food.id, it.quantity) }
            )
            inProgress.value = true
            delay(2000)
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