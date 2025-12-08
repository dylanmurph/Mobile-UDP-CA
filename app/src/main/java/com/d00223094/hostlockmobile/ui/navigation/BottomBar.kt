// this file creates the bottom navigation bar found on the main screens
package com.d00223094.hostlockmobile.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.d00223094.hostlockmobile.data.AccessLogs
import com.d00223094.hostlockmobile.data.AdminMaintenance
import com.d00223094.hostlockmobile.data.GuestManagement
import com.d00223094.hostlockmobile.data.Home
import com.d00223094.hostlockmobile.data.HostLockDestination

// data class to hold the information needed for each button on the bar
data class BottomNavItem(
    val destination: HostLockDestination,
    val icon: ImageVector,
    val contentDescription: String
)

// list of all bottom bar buttons
private val BottomNavItems = listOf(
    BottomNavItem(
        destination = Home,
        icon = Icons.Filled.Lock,
        contentDescription = "Home"
    ),
    BottomNavItem(
        destination = GuestManagement,
        icon = Icons.Filled.List,
        contentDescription = "Guest Management"
    ),
    BottomNavItem(
        destination = AccessLogs,
        icon = Icons.Filled.Menu,
        contentDescription = "Access Logs"
    ),
    BottomNavItem(
        destination = AdminMaintenance,
        icon = Icons.Filled.Build,
        contentDescription = "Admin Maintenance"
    )
)

// the main composable for the bottom navigation bar
@Composable
fun HostLockBottomBar(
    currentRoute: String?,
    onNavigateToDestination: (HostLockDestination) -> Unit
) {
    NavigationBar {
        // loop through each item to create a button
        BottomNavItems.forEach { item ->
            // check if the current route matches the button's route
            val isSelected = currentRoute == item.destination.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                label = { Text(item.destination.title) },
                selected = isSelected, // highlights the selected button
                onClick = {
                    // navigate only if it is not the current screen
                    if (currentRoute != item.destination.route) {
                        onNavigateToDestination(item.destination)
                    }
                }
            )
        }
    }
}