package com.d00223094.hostlockmobile

import android.app.Application
import com.d00223094.hostlockmobile.data.AppDatabase
import com.d00223094.hostlockmobile.data.AppRepository

class HostLockApplication : Application() {

    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    val repository: AppRepository by lazy {
        AppRepository(database.accessLogDao(), database.guestListDao())
    }
}