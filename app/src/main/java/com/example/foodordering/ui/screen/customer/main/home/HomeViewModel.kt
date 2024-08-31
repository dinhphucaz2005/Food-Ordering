package com.example.foodordering.ui.screen.customer.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    fun addCart(foodId: String) {
        viewModelScope.launch {
            when (val result = repository.addCart(foodId)) {
                is AppResource.Success -> {
                    Log.d(TAG, "Add cart success")
                }

                is AppResource.Error -> {
                    Log.d(TAG, result.error)
                }
            }
        }
    }

    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    val foods: StateFlow<List<Food>> = _foods


    init {
        viewModelScope.launch {
            when (val result = repository.getFoods()) {
                is AppResource.Success -> {
                    result.data?.let { _foods.value = it.map { foodDTOs -> Food(foodDTOs) } }
                    Log.d(TAG, "init: ${_foods.value}")
                }

                is AppResource.Error -> {
                    Log.d(TAG, result.error)
                }
            }
        }
    }
}