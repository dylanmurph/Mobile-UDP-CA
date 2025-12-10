// this is the main navigation host that manages screen switching and the bottom bar
package com.d00223094.hostlockmobile.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d00223094.hostlockmobile.data.AccessLogs
import com.d00223094.hostlockmobile.data.AdminMaintenance
import com.d00223094.hostlockmobile.data.DeviceViewModel
import com.d00223094.hostlockmobile.data.GuestManagement
import com.d00223094.hostlockmobile.data.Home
import com.d00223094.hostlockmobile.data.Login
import com.d00223094.hostlockmobile.ui.screens.AccessLogsScreen
import com.d00223094.hostlockmobile.ui.screens.AdminMaintenanceScreen
import com.d00223094.hostlockmobile.ui.screens.GuestManagementScreen
import com.d00223094.hostlockmobile.ui.screens.HomeScreen
import com.d00223094.hostlockmobile.ui.screens.LoginScreen

// list of screens where the bottom navigation bar should be visible
private val NAV_BAR_SCREENS = listOf(Home.route, GuestManagement.route, AccessLogs.route, AdminMaintenance.route)

// the entry point for app navigation
@Composable
fun AppNavigation(viewModel: DeviceViewModel) {
    // sets up the navigation controller
    val navController = rememberNavController()
    // tracks the currently visible route
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    // determines if the bottom bar should be shown
    val showBottomBar = currentRoute in NAV_BAR_SCREENS

    // scaffold provides the structure for the screen (e.g., area for bottom bar)
    Scaffold(
        bottomBar = {
            // only show the bottom bar for the main screens
            if (showBottomBar) {
                HostLockBottomBar(
                    currentRoute = currentRoute,
                    onNavigateToDestination = { destination ->
                        navController.navigate(destination.route) {
                            // pop up to the start to manage the back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true // avoid multiple copies of the same screen
                            restoreState = true // restore state when reselecting a tab
                        }
                    }
                )
            }
        }
    ) { padding ->
        // navhost manages the screen content based on the current route
        NavHost(
            navController = navController,
            startDestination = Login.route, // the first screen the user sees
            modifier = Modifier.padding(padding)
        ) {
            // define the login screen composable
            composable(Login.route) {
                LoginScreen(navController = navController, viewModel = viewModel)
            }
            // define the home screen composable
            composable(Home.route) {
                HomeScreen(navController)
            }
            // define the guest management screen composable
            composable(GuestManagement.route) {
                GuestManagementScreen(navController, viewModel = viewModel)
            }
            // define the access logs screen composable
            composable(AccessLogs.route) {
                AccessLogsScreen(navController, viewModel = viewModel)
            }

            // define the admin maintenance screen composable
            composable(AdminMaintenance.route) {
                AdminMaintenanceScreen(navController, viewModel = viewModel)
            }

        }
    }
}
