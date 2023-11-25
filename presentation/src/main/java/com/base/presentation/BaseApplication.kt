package com.base.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.domain.Coordinates
import com.base.presentation.features.details.NetworkDetailScreen
import com.base.presentation.features.list.NetworksListScreen
import com.base.presentation.features.splash.SplashScreen

@Composable
fun BaseApplication(openMaps: (coordinates: Coordinates) -> Unit) {
    val navController = rememberNavController()
    BaseNavHost(
        navController = navController,
        openMaps = openMaps
    )
}

@Composable
fun BaseNavHost(
    navController: NavHostController,
    openMaps: (coordinates: Coordinates) -> Unit,
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen({
                navController.navigate("networks") {
                    popUpTo(0)
                }
            })
        }
        composable("networks") {
            NetworksListScreen({ network -> navController.navigate("networks/${network.id}") })
        }
        composable(
            "networks/{networkId}"
        ) {
            NetworkDetailScreen(
                it.arguments?.getString("networkId"),
                { navController.navigateUp() },
                openMaps
            )
        }
    }
}