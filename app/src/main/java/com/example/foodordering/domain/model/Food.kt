package com.example.foodordering.domain.model

import kotlin.random.Random

data class Food(
    var id: String,
    var name: String,
    var price: Int,
    var gallery: List<String>,
) {

    constructor() : this("", "", 0, listOf()) {
        val rand = Random.nextInt(0, 3)

        id = "0xFFedc27$rand"
        name = listOf(
            "Ratatouille",
            "Paella",
            "Lasagna"
        )[rand]

        price = Random.nextInt(1, 5) * 100

        gallery = listOf(
            listOf(
                "https://1.bp.blogspot.com/-i1YPcdES0Jc/UT3Nwh9IJ1I/AAAAAAAABUc/R_PXP1hV97o/s1600/Ratatouille.jpg",
                "https://claudia.abril.com.br/wp-content/uploads/2020/01/pc3a1scoa-vegana-prato-principal-1-ratatouille-mizina-1.jpg",
                "https://i.pinimg.com/originals/55/2b/91/552b91ae3947ac3bb4ff803e76c1309e.jpg"
            ),
            listOf(
                "https://bing.com/th?id=OSK.c2100ca01c3f6c68a70ec033c6264396",
                "https://bing.com/th?id=OSK.cbd75c2471998748223ca996706ee9f6",
                "https://bing.com/th?id=OSK.8543b503b4fbb760620e771986a3134b",
            ),
            listOf(
                "https://th.bing.com/th/id/OIP.1h2GDwcRMCT0UdDxWxS_qwHaFO?rs=1&pid=ImgDetMain",
                "https://th.bing.com/th/id/OIP.dwMls67oZ064VooMolBBKQHaHa?w=480&h=480&rs=1&pid=ImgDetMain",
                "https://th.bing.com/th/id/OIP.uL0j2BHig_jbdMQOaFFk1AHaHa?w=600&h=600&rs=1&pid=ImgDetMain"
            )
        )[rand]

    }
}