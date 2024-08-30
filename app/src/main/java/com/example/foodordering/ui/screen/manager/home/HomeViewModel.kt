package com.example.foodordering.ui.screen.manager.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ManagerRepository
) : ViewModel() {
    val billList = mutableStateListOf<Bill>()

    init {
        viewModelScope.launch {
//            repository.getFoods()
            when (val result = repository.getBills()) {
                is AppResource.Success -> {
                    result.data?.let { billList.addAll(it) }
                }

                is AppResource.Error -> {
                    TODO("Handle Error")
                }
            }
        }
    }
}