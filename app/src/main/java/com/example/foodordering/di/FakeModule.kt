package com.example.foodordering.di

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.data.dto.InvoiceDTO
import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.domain.repository.ImageRepository
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.ui.screen.customer.authentication.login.LoginViewModel
import com.example.foodordering.ui.screen.customer.authentication.register.RegisterViewModel
import com.example.foodordering.ui.screen.customer.main.cart.CartViewModel
import com.example.foodordering.ui.screen.customer.main.confirm.ConfirmViewModel
import com.example.foodordering.ui.screen.customer.main.detail.DetailViewModel
import com.example.foodordering.ui.screen.customer.order.history.OrderHistoryViewModel
import com.example.foodordering.util.AppResource

object FakeModule {
    private val authRepository = object : AuthRepository {
        override suspend fun login(email: String, password: String): AppResource<UserDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun register(
            email: String,
            name: String,
            password: String,
            phoneNumber: String
        ): AppResource<UserDTO> {
            TODO("Not yet implemented")
        }
    }
    private val customerRepository = object : CustomerRepository {
        override suspend fun getFoods(): AppResource<List<FoodDTO>> {
            TODO("Not yet implemented")
        }

        override suspend fun addCart(productId: String): AppResource<CartDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun getFood(foodId: String): FoodDTO {
            TODO("Not yet implemented")
        }

        override suspend fun getCart(): AppResource<CartDTO> {
            TODO("Not yet implemented")
        }

        override suspend fun updateCart(
            foodId: String,
            cartId: String,
            quantity: Int
        ): AppResource<*> {
            TODO("Not yet implemented")
        }

        override suspend fun confirmCart(cartId: String): AppResource<*> {
            TODO("Not yet implemented")
        }

        override suspend fun getInvoices(): AppResource<List<InvoiceDTO>> {
            TODO("Not yet implemented")
        }

        override suspend fun getInvoice(id: String): InvoiceDTO? {
            TODO("Not yet implemented")
        }
    }
    private val imageRepository = object : ImageRepository {
        override suspend fun getImages(): List<Uri> {
            TODO("Not yet implemented")
        }

    }
    private val managerRepository = object : ManagerRepository {
        override suspend fun getFoods(): AppResource<List<Food>> {
            TODO("Not yet implemented")
        }

        override suspend fun getBills(): AppResource<List<Bill>> {
            TODO("Not yet implemented")
        }

        override suspend fun addFood(food: Food, imageList: List<Uri?>): AppResource<Food> {
            TODO("Not yet implemented")
        }

        override suspend fun removeFood(foodId: String): AppResource<String> {
            TODO("Not yet implemented")
        }

        override suspend fun uploadImagesSuspend(
            imageList: List<Uri?>,
            name: String
        ): List<String> {
            TODO("Not yet implemented")
        }
    }

    @Composable
    fun provideNavController(): NavHostController = NavHostController(LocalContext.current)

    fun provideCartViewModel(): CartViewModel = CartViewModel(customerRepository)
    fun provideDetailViewModel(): DetailViewModel = DetailViewModel(customerRepository)
    fun provideConfirmViewModel(): ConfirmViewModel = ConfirmViewModel(customerRepository)
    fun provideLoginViewModel(): LoginViewModel = LoginViewModel(authRepository)
    fun provideRegisterViewModel(): RegisterViewModel = RegisterViewModel(authRepository)
    fun provideOrderHistoryViewModel(): OrderHistoryViewModel =
        OrderHistoryViewModel(customerRepository)
}