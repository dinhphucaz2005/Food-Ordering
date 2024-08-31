package com.example.foodordering.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.foodordering.ui.screen.customer.authentication.login.LoginScreen
import com.example.foodordering.ui.screen.customer.authentication.register.RegisterScreen

fun NavGraphBuilder.authNavigation(navController: NavHostController) {

    navigation(startDestination = Routes.AUTH_LOGIN, route = Routes.AUTH) {

        composable(Routes.AUTH_LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.AUTH_REGISTER) {
            RegisterScreen(navController = navController, hiltViewModel())
        }

    }

}