package com.example.foodordering.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodordering.domain.model.Food
import com.example.foodordering.ui.screen.customer.Routes
import com.example.foodordering.ui.screen.customer.authentication.login.LoginScreen
import com.example.foodordering.ui.screen.customer.authentication.register.RegisterScreen
import com.example.foodordering.ui.screen.customer.cart.CartScreen
import com.example.foodordering.ui.screen.customer.cart.CartViewModel
import com.example.foodordering.ui.screen.customer.detail.DetailScreen
import com.example.foodordering.ui.screen.customer.home.HomeScreen

@Preview
@Composable
fun CustomerNavigation() {

    val cartViewModel: CartViewModel = viewModel()

    val navController = rememberNavController()

    var food: Food? = null

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(onLoginSuccess = {
                navController.popBackStack()
                navController.navigate(Routes.HOME)
            }, navigateRegister = {
                navController.navigate(Routes.REGISTER)
            })
        }
        composable(Routes.REGISTER) {
            RegisterScreen(onRegisterSuccess = {
                navController.popBackStack()
            })
        }
        composable(Routes.HOME) {
            HomeScreen(onCheckout = {
                navController.navigate(Routes.CART)
            }, cartViewModel = cartViewModel, gotoDetail = {
                food = it
                navController.navigate(Routes.DETAIL)
            })
        }
        composable(Routes.CART) {
            CartScreen(viewModel = cartViewModel, popBackStack = {
                navController.popBackStack()
            })
        }
        composable(Routes.DETAIL) {
            DetailScreen(food)
        }
    }
}