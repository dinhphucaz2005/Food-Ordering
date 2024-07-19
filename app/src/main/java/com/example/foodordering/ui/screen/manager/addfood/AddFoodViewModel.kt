package com.example.foodordering.ui.screen.manager.addfood

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodordering.data.repository.ImageRepositoryImpl

class AddFoodViewModel : ViewModel() {

//    val database = AppModule.provideDatabase()



    fun addImage() {
        TODO("Not yet implemented")
    }

    fun addToFireBase() {
        TODO("Not yet implemented")
    }

    val imageListState = mutableStateListOf<Uri>(Uri.parse("SDLf"))

    val idState = mutableStateOf("")
    val nameState = mutableStateOf("")
    val priceState = mutableStateOf("")

}