package com.example.foodordering.domain.model

import com.example.foodordering.data.dto.InvoiceDTO

data class Invoice(
    val id: String,
    val foods: List<Food>,
    val userId: String,
    val total: Int,
    val status: Boolean,
    val dateCreated: String,
) {
    constructor(invoiceDTO: InvoiceDTO) : this(
        invoiceDTO.id,
        invoiceDTO.foods.map { Food(it) },
        invoiceDTO.userId,
        invoiceDTO.total,
        invoiceDTO.status,
        invoiceDTO.dateCreated.split("T")[0]
    )

    fun toCart(): Cart {
        return Cart(id, foods, total)
    }
}
