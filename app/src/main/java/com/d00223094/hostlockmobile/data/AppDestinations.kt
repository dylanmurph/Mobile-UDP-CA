// this file defines the navigation routes and titles for the whole app
package com.d00223094.hostlockmobile.data

// interface for all app destinations
sealed interface HostLockDestination {
    val route: String // unique key for navigation
    val title: String // display name
}

// each object defines a main screen and its route
object Login : HostLockDestination {
    override val route = "login"
    override val title = "Login"
}

object Home : HostLockDestination {
    override val route = "home"
    override val title = "Home"
}

object GuestManagement : HostLockDestination {
    override val route = "guest_management"
    override val title = "Guests"
}

object AccessLogs : HostLockDestination {
    override val route = "access_logs"
    override val title = "Logs"
}

object AdminMaintenance : HostLockDestination {
    override val route = "admin_maintenance"
    override val title = "Admin"
}