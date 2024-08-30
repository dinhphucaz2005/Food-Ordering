package com.example.foodordering.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.foodordering.ui.screen.customer.main.cart.CartScreen
import com.example.foodordering.ui.screen.customer.main.confirm.ConfirmScreen
import com.example.foodordering.ui.screen.customer.main.detail.DetailScreen
import com.example.foodordering.ui.screen.customer.main.home.HomeScreen

fun NavGraphBuilder.mainNavigation(navController: NavHostController) {

    navigation(startDestination = Routes.MAIN_HOME, route = Routes.MAIN) {

        composable(Routes.MAIN_HOME) { HomeScreen(navController) }
        composable(Routes.MAIN_CART) { CartScreen(navController) }
        composable(Routes.MAIN_DETAIL + "/{foodId}") { backstackEntry ->
            val id = backstackEntry.arguments?.getString("foodId")
            if (id != null)
                DetailScreen(id)
            else
                navController.popBackStack()
        }
        composable(Routes.MAIN_CONFIRM) { ConfirmScreen(navController) }
    }

}