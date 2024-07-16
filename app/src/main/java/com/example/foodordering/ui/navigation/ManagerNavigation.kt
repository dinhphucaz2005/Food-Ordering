package com.example.foodordering.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodordering.ui.component.TopAppBarHome
import com.example.foodordering.ui.screen.manager.ManagerRoutes
import com.example.foodordering.ui.screen.manager.addfood.AddFood
import com.example.foodordering.ui.screen.manager.addfood.AddFoodViewModel
import com.example.foodordering.ui.screen.manager.detail.DetailScreen
import com.example.foodordering.ui.screen.manager.help.HelpScreen
import com.example.foodordering.ui.screen.manager.home.HomeScreen
import com.example.foodordering.ui.screen.manager.home.ManagerDrawer
import com.example.foodordering.ui.screen.manager.setting.SettingScreen
import com.example.foodordering.ui.screen.manager.statistic.StatisticScreen
import com.example.foodordering.ui.theme.Background
import com.example.foodordering.ui.theme.Tertiary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ManagerNavigation() {

    val addFoodViewModel: AddFoodViewModel = viewModel()

    val navController = rememberNavController()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ManagerDrawer(
                onClick = listOf(
                    {
                        // Home Click
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != ManagerRoutes.HOME)
                            navController.navigate(ManagerRoutes.HOME)
                    },
                    {
                        // Food Click
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != ManagerRoutes.FOOD)
                            navController.navigate(ManagerRoutes.FOOD)
                    },
                    {
                        // Statistic Click
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != ManagerRoutes.STATISTIC)
                            navController.navigate(ManagerRoutes.STATISTIC)
                    },
                    {
                        // Setting Click
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != ManagerRoutes.SETTING)
                            navController.navigate(ManagerRoutes.SETTING)
                    },
                    {
                        // Help Click
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != ManagerRoutes.HELP)
                            navController.navigate(ManagerRoutes.HELP)
                    },
                )
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .background(Background)
                .fillMaxSize(),
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Add food") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        showBottomSheet = true
                    },
                    shape = RoundedCornerShape(8.dp),
                    containerColor = Tertiary,
                )
            },
            topBar = {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Tertiary)
                        .height(80.dp)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TopAppBarHome("Food Manager") {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                }
            },
            content = { contentPadding ->

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {

                        AddFood(onCancel = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }, viewModel = addFoodViewModel)
                    }
                }

                NavHost(navController = navController, startDestination = ManagerRoutes.HOME)
                {
                    composable(ManagerRoutes.HOME) {
                        HomeScreen(
                            Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        )
                    }
                    composable(ManagerRoutes.FOOD) {
                        DetailScreen(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        )
                    }
                    composable(ManagerRoutes.STATISTIC) {
                        StatisticScreen(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        )
                    }
                    composable(ManagerRoutes.SETTING) {
                        SettingScreen(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        )
                    }
                    composable(ManagerRoutes.HELP) {
                        HelpScreen(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        )
    }
}