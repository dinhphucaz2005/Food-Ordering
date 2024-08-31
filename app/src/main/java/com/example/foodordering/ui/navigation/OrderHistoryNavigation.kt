package com.example.foodordering.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.foodordering.ui.screen.customer.order.detail.OrderHistoryDetailScreen
import com.example.foodordering.ui.screen.customer.order.history.OrderHistoryScreen

fun NavGraphBuilder.orderHistoryNavigation(navController: NavHostController) {

    navigation(startDestination = Routes.ORDERHISTORY_MAIN, route = Routes.ORDERHISTORY) {
        composable(Routes.ORDERHISTORY_MAIN) { OrderHistoryScreen(navController, hiltViewModel()) }
        composable(Routes.ORDERHISTORY_DETAIL + "/" + "{invoiceId}") { backstackEntry ->
            val id = backstackEntry.arguments?.getString("invoiceId")
            if (id != null)
                OrderHistoryDetailScreen(id, navController, hiltViewModel())
            else
                navController.popBackStack()
        }
    }

}