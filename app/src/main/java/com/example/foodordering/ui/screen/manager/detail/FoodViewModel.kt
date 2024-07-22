package com.example.foodordering.ui.screen.manager.detail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {

    var listFoodState = mutableStateListOf<Food>()

    private val repository by lazy {
        AppModule.provideManagerRepository()
    }

    fun removeFood(foodId: String) {
        viewModelScope.launch {
            when (val result = repository.removeFood(foodId)) {
                is AppResource.Success -> {
                    result.data?.let {
                        listFoodState.removeIf {
                            it.id == foodId
                        }
                    }
                }

                is AppResource.Error -> {
                    val message = result.error
                    TODO("Show error")
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            when (val result = repository.getFoods()) {
                is AppResource.Success -> {
                    result.data?.let { listFoodState.addAll(it) }
                }

                is AppResource.Error -> {
                    val message = result.error
                    TODO("Show error")
                }
            }
        }
    }

}