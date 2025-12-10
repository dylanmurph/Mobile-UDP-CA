package com.d00223094.hostlockmobile.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class UserDataStore(private val context: Context) {

    companion object {
        val THEME_KEY = stringPreferencesKey("theme_preference")
    }

    val themePreference: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: "Light"
        }

    suspend fun saveThemePreference(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }
}
