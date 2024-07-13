package com.example.foodordering.ui.screen.customer.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var listFoodState = mutableStateListOf<Food>()

    init {
        viewModelScope.launch {
            listFoodState.clear()
            listFoodState.addAll(FakeData.provideListFood())
        }
    }

    private val repository by lazy {
        AppModule.provideCustomerRepository()
    }


}