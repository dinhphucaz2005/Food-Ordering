package com.example.foodordering.ui.tmpnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.foodordering.ui.navigation.Routes
import com.example.foodordering.ui.navigation.authNavigation
import com.example.foodordering.ui.navigation.mainNavigation

@Composable
fun CustomerNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.AUTH) {
        authNavigation(navController)
        mainNavigation(navController)
    }

}