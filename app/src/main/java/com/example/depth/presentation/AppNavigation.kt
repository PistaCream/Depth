package com.example.depth.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.depth.presentation.history.HistoryScreen
import com.example.depth.presentation.history.HistoryViewModel
import com.example.depth.presentation.home.HomeScreen
import com.example.depth.presentation.timer.TimerScreen
import com.example.depth.presentation.timer.TimerViewModel

data class BottomNavItem(
    val route: String,
    val title: String,
)

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    historyViewModel: HistoryViewModel,
    timerViewModel: TimerViewModel
) {
    val items = listOf(
        BottomNavItem("history", "History"),
        BottomNavItem("main", "Home"),
        BottomNavItem("timer", "Timer")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Text(item.title.first().toString()) },
                        label = { Text(item.title) },
                        selected = currentDestination?.route == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("history") {
                HistoryScreen(historyViewModel)
            }


            composable("main") {
                HomeScreen()
            }

            composable("timer") {
                TimerScreen(timerViewModel)
            }
        }
    }
}