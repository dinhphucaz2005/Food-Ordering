package com.example.foodordering.ui.screen.customer.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository by lazy {
        AppModule.provideCustomerRepository()
    }

    var listFoodState = mutableStateListOf<Food>()

    init {
        viewModelScope.launch {
            listFoodState.clear()
            when (val result = repository.getFoods()) {
                is AppResource.Success -> {
                    result.data?.let {
                        listFoodState.addAll(it)
                    }
                }

                else -> {
                    println(result)
                }
            }
        }
    }
}