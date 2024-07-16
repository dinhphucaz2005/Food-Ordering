package com.example.foodordering.ui.screen.manager.addfood

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddFoodViewModel : ViewModel() {

//    val database = AppModule.provideDatabase()



    fun addImage() {

    }

    fun addToFireBase() {
//        FakeData.provideListFood().forEach {
//            database.child("test").setValue(Food())
//                .addOnSuccessListener {
//                }
//                .addOnFailureListener {
//                    val res = 0
//                }
//        }
    }

    val imageListState = mutableStateListOf<Uri>(Uri.parse("SDLf"))

    val idState = mutableStateOf("")
    val nameState = mutableStateOf("")
    val priceState = mutableStateOf("")

}