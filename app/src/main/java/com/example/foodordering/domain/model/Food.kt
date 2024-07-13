package com.example.foodordering.domain.model

data class Food(
    val id: String,
    val name: String,
    val price: Long,
    val gallery: List<String>,
) {

    constructor() : this(
        getRandomId(),
        getRandomName(),
        getRandOmTotalPrice(),
        getRandomGallery()
    )
}

fun getRandomGallery(): List<String> {
    return listOf(
        "https://fastly.picsum.photos/id/565/200/200.jpg?hmac=QvKo8qgzFFNcZoXCpT0CNMDTwWd3ynwqLXxrzK2o8fw",
        "https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U",
        "https://fastly.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"
    )
}

fun getRandomName(): String {
    return listOf(
        "Ratatouille",
        "Paella",
        "Lasagna"
    ).random()
}