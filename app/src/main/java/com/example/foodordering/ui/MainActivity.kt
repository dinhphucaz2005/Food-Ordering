package com.example.foodordering.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.foodordering.ui.navigation.CustomerNavigation
import com.example.foodordering.ui.screen.manager.detail.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           CustomerNavigation()
            DetailScreen()
        }
    }
}
