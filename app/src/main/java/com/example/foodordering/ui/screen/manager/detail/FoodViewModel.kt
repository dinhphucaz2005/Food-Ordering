package com.example.foodordering.ui.screen.manager.detail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: ManagerRepository
) : ViewModel() {

    var listFoodState = mutableStateListOf<Food>()

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

    fun getFood(foodId: String) {

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