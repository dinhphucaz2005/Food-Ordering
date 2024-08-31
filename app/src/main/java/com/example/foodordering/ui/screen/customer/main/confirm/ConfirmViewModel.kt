package com.example.foodordering.ui.screen.customer.main.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.domain.model.Cart
import com.example.foodordering.domain.model.EventData
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    fun confirm(callback: () -> Unit) {
        viewModelScope.launch {
            _cart.value?.let {
                when (val result = repository.confirmCart(it.id)) {
                    is AppResource.Success -> {
                        EventBus.getDefault().postSticky(EventData("Order successfully"))
                    }

                    is AppResource.Error -> {
                        EventBus.getDefault().postSticky(EventData(result.error))
                    }
                }
            }
            callback()
        }
    }

    private val _cart = MutableStateFlow<Cart?>(null)

    val cart: StateFlow<Cart?> = _cart.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = repository.getCart()) {
                is AppResource.Success -> {
                    _cart.value = result.data?.let { Cart(it) }
                }

                is AppResource.Error -> {
                    println(result.error)
                }
            }
        }
    }

}