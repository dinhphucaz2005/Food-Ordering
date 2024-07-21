package com.example.foodordering.ui.screen.manager.detail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.AppModule
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository by lazy {
        AppModule.provideManagerRepository()
    }

    var listFoodState = mutableStateListOf<Food>()


    init {
        viewModelScope.launch {
        }
    }

}