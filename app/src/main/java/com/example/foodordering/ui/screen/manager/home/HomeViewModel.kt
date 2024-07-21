package com.example.foodordering.ui.screen.manager.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.data.repository.ManagerRepositoryImpl
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.util.AppResource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val repository = AppModule.provideManagerRepository()

    val billList = mutableStateListOf<Bill>()

    init {
        viewModelScope.launch {
            repository.getFoods()
        }
    }
}