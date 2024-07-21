package com.example.foodordering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.foodordering.di.AppModule
import com.example.foodordering.ui.navigation.PreNavigation

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PreNavigation()
//            CustomerNavigation()
//            ManagerNavigation()
//            WaitingScreen()
//            HomeScreen()
        }
    }
}
