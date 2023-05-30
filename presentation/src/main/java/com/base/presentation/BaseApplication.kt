package com.base.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.presentation.features.home.HomeScreen
import com.base.presentation.features.setting.SettingScreen

@Composable
fun BaseApplication() {
    val navController = rememberNavController()
    BaseNavHost(
        navController = navController,
    )
}

@Composable
fun BaseNavHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen({ navController.navigate("setting") })
        }
        composable(
            "setting"
        ) {
            SettingScreen({ navController.navigateUp() })
        }
    }
}