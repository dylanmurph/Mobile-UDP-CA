package com.d00223094.hostlockmobile.data

class AppRepository {

    fun loadAccessLogs(): List<AccessLog> {
        return (1..20).map {
            AccessLog(
                id = it,
                summary = "Access Log $it",
                details = "Detailed information for Access Log $it."
            )
        }
    }

    fun loadGuestList(): List<GuestList> {
        return (1..20).map {
            GuestList(
                id = it,
                summary = "guest $it",
                details = "Detailed information for guest $it."
            )
        }
    }
}
