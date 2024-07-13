package com.example.foodordering.ui.screen.manager.detail

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodordering.di.AppModule
import com.example.foodordering.di.FakeData
import com.example.foodordering.domain.model.Food

class AddFoodViewModel : ViewModel() {

    val database = AppModule.provideDatabase()

    fun addImage() {
        TODO("Not yet implemented")
    }

    fun addToFireBase() {
        FakeData.provideListFood().forEach {
            database.child("test").setValue(Food())
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                    val res = 0
                }
        }
    }

    val imageListState = mutableStateListOf<Uri>(Uri.parse("SDLf"))

    val idState = mutableStateOf("")
    val nameState = mutableStateOf("")
    val priceState = mutableStateOf("")


}