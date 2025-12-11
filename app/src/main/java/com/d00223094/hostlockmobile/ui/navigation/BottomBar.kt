// this file creates the bottom navigation bar found on the main screens
package com.d00223094.hostlockmobile.ui.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.d00223094.hostlockmobile.data.AccessLogs
import com.d00223094.hostlockmobile.data.AdminMaintenance
import com.d00223094.hostlockmobile.data.GuestManagement
import com.d00223094.hostlockmobile.data.Home
import com.d00223094.hostlockmobile.data.HostLockDestination
import com.d00223094.hostlockmobile.data.Login

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
        icon = Icons.Filled.Home,
        contentDescription = "Home"
    ),
    BottomNavItem(
        destination = GuestManagement,
        icon = Icons.Filled.People,
        contentDescription = "Guests"
    ),
    BottomNavItem(
        destination = AccessLogs,
        icon = Icons.Filled.History,
        contentDescription = "Logs"
    ),
    BottomNavItem(
        destination = AdminMaintenance,
        icon = Icons.Filled.Build,
        contentDescription = "Admin"
    ),
    BottomNavItem(
        destination = Login,
        icon = Icons.Filled.ExitToApp,
        contentDescription = "Logout"
    )
)

// the main composable for the bottom navigation bar
@Composable
fun HostLockBottomBar(
    currentRoute: String?,
    onNavigateToDestination: (HostLockDestination) -> Unit
) {
    val TAG = "HostLockBottomBar"
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
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
                label = { Text(if(item.destination == Login) "Logout" else item.destination.title) },
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                onClick = {
                    Log.d(TAG, "Bottom navigation item clicked: ${item.contentDescription}")
                    // navigate only if it is not the current screen
                    if (currentRoute != item.destination.route) {
                        Log.d(TAG, "Navigating to ${item.destination.route}")
                        onNavigateToDestination(item.destination)
                    }
                }
            )
        }
    }
}
