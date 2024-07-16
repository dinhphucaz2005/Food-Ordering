package com.example.foodordering.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.foodordering.ui.navigation.ManagerNavigation
import com.example.foodordering.ui.screen.manager.home.HomeScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            CustomerNavigation()
            ManagerNavigation()
//            WaitingScreen()
//            HomeScreen()
        }
    }
}
