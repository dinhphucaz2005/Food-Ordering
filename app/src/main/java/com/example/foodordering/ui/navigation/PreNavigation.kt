package com.example.foodordering.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodordering.ui.screen.splash.TempScreen


@Preview
@Composable
fun PreNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            TempScreen(
                navigateToManager = {
                    navController.navigate("manager")
                },
                navigateToCustomer = {
                    navController.navigate("customer")
                }
            )
        }
        composable("manager") {
            ManagerNavigation()
        }
        composable("customer") {
            CustomerNavigation()
        }
    }

}