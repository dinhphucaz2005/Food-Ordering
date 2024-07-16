package com.example.foodordering.ui.screen.manager.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.data.repository.ManagerRepositoryImpl
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.util.AppResource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val repository = ManagerRepositoryImpl()

    val billList = mutableStateListOf<Bill>()

    init {
        viewModelScope.launch {
            when (val result = repository.getBills()) {
                is AppResource.Success -> {
                    result.data?.let { billList.addAll(it) }
                }

                else -> {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}