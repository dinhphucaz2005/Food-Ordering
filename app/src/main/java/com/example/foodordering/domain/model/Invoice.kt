package com.example.foodordering.domain.model

import kotlin.random.Random

data class Invoice(
    val id: String,
    val invoiceDate: String,
    val totalPrice: Long,
    val tableNumber: Int,
    val status: String,
    val type: String,
) {
    constructor() : this(
        getRandomId(),
        getRandomInvoiceDate(),
        getRandOmTotalPrice(),
        Random.nextInt(1, 12),
        getRandomStatus(),
        getRandomCustomerType()
    )

}

fun getRandOmTotalPrice(): Long {
    return Random.nextInt(10, 10000).toLong()
}

fun getRandomCustomerType(): String {
    val allowedCustomerTypes = listOf("Qua app", "Khách vãng lai")
    return allowedCustomerTypes.random()
}

fun getRandomStatus(): String {
    val allowedStatuses = listOf("Chưa giao hàng", "Đang xử lý", "Hoàn thành", "Đã hủy")
    return allowedStatuses.random()
}

fun getRandomId(): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..8).map { allowedChars.random() }.joinToString("")
}

private fun getRandomInvoiceDate(): String {
    val startYear = 2023
    val endYear = 2024
    val randomYear = startYear + Random.nextInt(endYear - startYear)
    return "$randomYear-${Random.nextInt(1, 12).toString().padStart(2, '0')}-${
        Random.nextInt(1, 28).toString().padStart(2, '0')
    }"
}


private fun getRandomAmount(): Long {
    return Random.nextLong(1, 1000)
}