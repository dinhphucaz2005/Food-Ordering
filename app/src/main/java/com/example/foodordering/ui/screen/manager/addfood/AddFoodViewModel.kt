package com.example.foodordering.ui.screen.manager.addfood

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.FoodHelper
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    companion object {
        private const val TAG = "AddFoodViewModel"
        const val SUCCESS_MESSAGE = "Food added successfully"
        const val NAME_ERROR_MESSAGE = "Please enter a name"
        const val PRICE_ERROR_MESSAGE = "Please enter a price"
    }

    private val repository = AppModule.provideManagerRepository()

    val imageListState = mutableStateOf<List<Uri?>>(emptyList())
    val nameState = mutableStateOf("test")
    val priceState = mutableStateOf("500")

    var message = mutableStateOf("")


    private var isAddFoodLoading = false
    fun addFood() {
        if (isAddFoodLoading)
            return
        viewModelScope.launch {
            isAddFoodLoading = true
            if (nameState.value.isEmpty()) {
                message.value = NAME_ERROR_MESSAGE
                return@launch
            }
            if (priceState.value.isEmpty()) {
                message.value = PRICE_ERROR_MESSAGE
                return@launch
            }
            val food = Food(
                FoodHelper.getIdFromFoodName(nameState.value),
                nameState.value,
                priceState.value.toInt(),
                emptyList()
            )
            when (val result = repository.addFood(food, imageListState.value)) {
                is AppResource.Success -> {
                    message.value = SUCCESS_MESSAGE
                    resetFood()
                }

                is AppResource.Error -> {
                    message.value = result.error
                }
            }
            isAddFoodLoading = false
        }
    }

    private fun resetFood() {
        nameState.value = ""
        priceState.value = ""
        imageListState.value = emptyList()
    }
}