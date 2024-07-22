package com.example.foodordering.data.repository.firebase

import android.net.Uri
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.FirebaseChild
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FManagerRepositoryImpl(
    private val database: DatabaseReference,
    private val storage: StorageReference,
) : ManagerRepository {

    companion object {
        const val REMOVE_SUCCESS_MESSAGE = "Food removed successfully"
    }

    override suspend fun getFoods(): AppResource<List<Food>> {
        return suspendCoroutine { continuation ->
            val dataRef = database.child(FirebaseChild.FOOD)
            dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val foodList = mutableListOf<Food>()
                    for (foodSnapshot in dataSnapshot.children) {
                        val food = foodSnapshot.getValue(Food::class.java)
                        food?.let { foodList.add(it) }
                    }
                    continuation.resume(AppResource.Success(foodList))
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    continuation.resume(AppResource.Error(databaseError.message))
                }
            })
        }
    }

    override suspend fun getBills(): AppResource<List<Bill>> {
        return suspendCoroutine { continuation ->
            val dataRef = database.child(FirebaseChild.BILL)
            dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val billList = mutableListOf<Bill>()
                    for (billSnapshot in snapshot.children) {
                        val bill = billSnapshot.getValue(Bill::class.java)
                        bill?.let { billList.add(it) }
                    }
                    continuation.resume(AppResource.Success(billList))
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    override suspend fun addFood(food: Food, imageList: List<Uri?>): AppResource<Food> {

        {
//        val foodList = mutableListOf(
//            Food(
//                id = "0xFF54cbcd",
//                name = "Ratatouille",
//                price = 500,
//                gallery = listOf(
//                    "https://1.bp.blogspot.com/-i1YPcdES0Jc/UT3Nwh9IJ1I/AAAAAAAABUc/R_PXP1hV97o/s1600/Ratatouille.jpg",
//                    "https://claudia.abril.com.br/wp-content/uploads/2020/01/pc3a1scoa-vegana-prato-principal-1-ratatouille-mizina-1.jpg",
//                    "https://i.pinimg.com/originals/55/2b/91/552b91ae3947ac3bb4ff803e76c1309e.jpg"
//                )
//            ),
//            Food(
//                id = "0xFF99d0f7",
//                name = "Paella",
//                price = 300,
//                gallery = listOf(
//                    "https://bing.com/th?id=OSK.c2100ca01c3f6c68a70ec033c6264396",
//                    "https://bing.com/th?id=OSK.cbd75c2471998748223ca996706ee9f6",
//                    "https://bing.com/th?id=OSK.8543b503b4fbb760620e771986a3134b"
//                )
//            ),
//            Food(
//                id = "0xFF905686",
//                name = "Lasagna",
//                price = 900,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.1h2GDwcRMCT0UdDxWxS_qwHaFO?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.dwMls67oZ064VooMolBBKQHaHa?w=480&h=480&rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.uL0j2BHig_jbdMQOaFFk1AHaHa?w=600&h=600&rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFFc7c0f3",
//                name = "Pizza",
//                price = 200,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.8UeIFPMYwIErE1ShRYB9QAAAAA?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.XzUnt5Afjbz_9Bmkng2IMQHaEK?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.naEpuF9k7LB0PYghldofMgHaE7?rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFF673ab7",
//                name = "Sushi",
//                price = 800,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/R.70e894a9ef389bdd04250d7e552cbdb3?rik=pUvKGMV38lryEQ&pid=ImgRaw&r=0",
//                    "https://th.bing.com/th/id/OIP.Cxix4QBBta3OSZY2bqhYxAHaFX?w=550&h=398&rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.LOBxvLsZ9OJB2QhGgqLvzQAAAA?w=474&h=315&rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFF8e24aa",
//                name = "Burger",
//                price = 350,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.g_EYshV4TBrKFonMmN2KEgHaE7?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.hpA3886C6M14lh_kZldpjwHaE8?w=600&h=400&rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.RSg1x-BLkrRHA9LFFaKrogHaE_?w=846&h=571&rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFF03a9f4",
//                name = "Tacos",
//                price = 250,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.ywM_T_rM7jFED-ubeSJqaAHaE8?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.CfYsJ0yA5QVI1hY3gt8tywHaE8?w=864&h=576&rs=1&pid=ImgDetMain",
//                    "https://i.pinimg.com/originals/a1/31/11/a13111f909a4f809103970ecde932b72.jpg"
//                )
//            ),
//            Food(
//                id = "0xFF4caf50",
//                name = "Falafel",
//                price = 400,
//                gallery = listOf(
//                    "https://media.timeout.com/images/105106289/image.jpg",
//                    "https://th.bing.com/th/id/OIP.BRJFK2P5UdgpJCiXqASMkAHaJ3?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.2ZZldd7BVmTSZEtJtaTshgHaLG?w=683&h=1024&rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFF9c27b0",
//                name = "Pad Thai",
//                price = 550,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.Hb8l5cAsfnY-kIvOu_TuTgHaLH?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.22FRkI4whqof1ZrqcSxUvQHaLF?w=736&h=1101&rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.cCcpHtzDokH0ixXcUc18_AHaLH?rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFFe91e63",
//                name = "Gelato",
//                price = 600,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/R.e9580ec1def64453e75648a450ae027f?rik=cwcaVtxTaJKFtA&pid=ImgRaw&r=0",
//                    "https://th.bing.com/th/id/R.cc33c49dbd34c6eba1d95713a540de8c?rik=Md4rGLqdzjpDMg&pid=ImgRaw&r=0",
//                    "https://th.bing.com/th/id/OIP.L7KZQNSglsWZP9Pa9B6gYAHaEK?rs=1&pid=ImgDetMain"
//                )
//            ),
//            Food(
//                id = "0xFFf44336",
//                name = "Churros",
//                price = 150,
//                gallery = listOf(
//                    "https://th.bing.com/th/id/OIP.IidV0PchIWUuqRTXeqKzrwAAAA?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.N_SKyArFcjwfUwTtbbTXqgHaLH?rs=1&pid=ImgDetMain",
//                    "https://th.bing.com/th/id/OIP.lUqlqqyfqSHsk60PCH5XEgHaLH?rs=1&pid=ImgDetMain"
//                )
//            )
//        )
//
//        foodList.forEachIndexed { index, food ->
//            val newFood = food.copy(
//                id = FoodHelper.getIdFromFoodName(food.name),
//                name = food.name,
//                price = food.price,
//                gallery = food.gallery
//            )
//            foodList[index] = newFood
//        }
//
//        return suspendCoroutine { continuation ->
//            val countDownLatch = CountDownLatch(foodList.size)
//            foodList.forEach { food ->
//                database.child(FirebaseChild.FOOD).child(food.id).setValue(food)
//                    .addOnSuccessListener {
//                        countDownLatch.countDown()
//                        if (countDownLatch.count == 0L) {
//                            continuation.resume(AppResource.Error("SUCCESS"))
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        continuation.resume(AppResource.Error(exception.message.toString()))
//                    }
//            }
//        }
        }

        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                val listUrl = uploadImagesSuspend(imageList, food.name)
                food.gallery = listUrl
                database.child(FirebaseChild.FOOD).child(food.id).setValue(food)
                    .addOnSuccessListener {
                        continuation.resume(AppResource.Success(food))
                    }
                    .addOnFailureListener { exception ->
                        exception.message?.let { AppResource.Error(it) }
                            ?.let { continuation.resume(it) }
                    }
            }
        }
    }

    override suspend fun removeFood(foodId: String): AppResource<String> {
        return suspendCoroutine { continuation ->
            val foodRef = database.child(FirebaseChild.FOOD).child(foodId)
            foodRef.removeValue()
                .addOnSuccessListener {
                    continuation.resume(AppResource.Success(REMOVE_SUCCESS_MESSAGE))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(AppResource.Error(exception.message.toString()))
                }
        }
    }

    override suspend fun uploadImagesSuspend(
        imageList: List<Uri?>,
        name: String
    ): List<String> {
        return suspendCoroutine { continuation ->
            val imageUrls = mutableListOf<String>()
            val countDownLatch = CountDownLatch(imageList.size)

            imageList.forEachIndexed { index, imageUri ->
                if (imageUri != null) {
                    val imageRef = storage.child("${FirebaseChild.IMAGE}/$name [$index]")
                    imageRef.putFile(imageUri)
                        .addOnSuccessListener { _ ->
                            imageRef.downloadUrl.addOnSuccessListener { uri ->
                                countDownLatch.countDown()
                                val downloadUrl = uri.toString()
                                imageUrls.add(downloadUrl)
                                if (countDownLatch.count == 0L) {
                                    continuation.resume(imageUrls)
                                }
                            }.addOnFailureListener {
                                countDownLatch.countDown()
                                if (countDownLatch.count == 0L) {
                                    continuation.resume(imageUrls)
                                }
                            }

                        }
                        .addOnFailureListener { _ ->
                            countDownLatch.countDown()
                        }
                }
            }
        }
    }

}