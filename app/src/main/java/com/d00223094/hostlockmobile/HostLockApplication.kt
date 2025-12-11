package com.d00223094.hostlockmobile

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.d00223094.hostlockmobile.data.AppDatabase
import com.d00223094.hostlockmobile.data.AppRepository
import com.d00223094.hostlockmobile.data.UserDataStore
import androidx.datastore.core.DataStore

private const val USER_PREFERENCES_NAME = "user_preferences"
private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)
class HostLockApplication : Application() {

    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val userDataStore by lazy { UserDataStore(this) }
    val repository: AppRepository by lazy {
        AppRepository(
            database.accessLogDao(),
            database.guestListDao(),
            database.usersDao(),
            userDataStore
        )
    }
}


