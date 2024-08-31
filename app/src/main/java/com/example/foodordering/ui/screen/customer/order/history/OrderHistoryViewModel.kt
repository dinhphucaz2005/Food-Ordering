package com.example.foodordering.ui.screen.customer.order.history

import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Invoice
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    private val _invoices = MutableStateFlow<List<Invoice>>(emptyList())
    val invoices: StateFlow<List<Invoice>> = _invoices.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = repository.getInvoices()) {
                is AppResource.Success -> {
                    result.data?.let {
                        _invoices.value = it.map { invoiceDTO -> Invoice(invoiceDTO) }
                    }
                }

                is AppResource.Error -> {
                    // TODO(Handle error)
                }
            }
        }

    }
}