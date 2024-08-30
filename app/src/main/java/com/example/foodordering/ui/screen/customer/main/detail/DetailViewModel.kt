package com.example.foodordering.ui.screen.customer.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodordering.data.repository.CustomerRepositoryImpl
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    fun getFood(foodId: String) {
        viewModelScope.launch {
            _food.value = Food(customerRepository.getFoodById(foodId))
        }
    }

    private val _food = MutableStateFlow<Food?>(null)
    val food: StateFlow<Food?> = _food.asStateFlow()

}