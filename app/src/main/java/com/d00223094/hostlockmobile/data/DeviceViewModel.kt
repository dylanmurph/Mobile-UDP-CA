package com.d00223094.hostlockmobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.d00223094.hostlockmobile.network.MarsApi
import com.d00223094.hostlockmobile.ui.MarsUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class DeviceViewModel(private val repository: AppRepository) : ViewModel() {
    private val _loggedInUserId = MutableStateFlow<Int?>(null)
    val loggedInUserId: StateFlow<Int?> = _loggedInUserId.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val accessLogs: StateFlow<List<AccessLog>> = _loggedInUserId.flatMapLatest { userId ->
        if (userId != null) {
            repository.getAllAccessLogsForUser(userId)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val guestList: StateFlow<List<GuestList>> = _loggedInUserId.flatMapLatest { userId ->
        if (userId != null) {
            repository.getAllGuestsForUser(userId)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onLoginSuccess(userId: Int) {
        _loggedInUserId.value = userId
    }

    fun addAccessLog(property: String, fobNumber: String, status: String, date: String) {
        _loggedInUserId.value?.let { userId ->
            viewModelScope.launch {
                repository.insertAccessLog(property, fobNumber, status, date, userId)
            }
        }
    }

    fun addGuest(name: String, email: String, property: String, fobNumber: String) {
        _loggedInUserId.value?.let { userId ->
            viewModelScope.launch {
                repository.insertGuest(name, email, property, fobNumber, userId)
            }
        }
    }

    val users: StateFlow<List<Users>> = repository.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteAccessLog(id: Int) {
        viewModelScope.launch {
            repository.deleteAccessLogById(id)
        }
    }

    fun deleteGuest(id: Int) {
        viewModelScope.launch {
            repository.deleteGuestById(id)
        }
    }

    fun clearAllAccessLogs() {
        viewModelScope.launch {
            repository.deleteAllAccessLogs()
        }
    }

    fun clearAllGuests() {
        viewModelScope.launch {
            repository.deleteAllGuests()
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            repository.deleteAllAccessLogs()
            repository.deleteAllGuests()
        }
    }

    suspend fun addUser(name: String, email: String, password: String): Long {
        return repository.insertUser(Users(name = name, email = email, password = password))
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            repository.deleteUserById(id)
        }
    }

    fun clearAllUsers() {
        viewModelScope.launch {
            repository.deleteAllUsers()
        }
    }

    suspend fun getUserByName(username: String): Users? {
        return repository.getUserByName(username)
    }

    // --- Theme Preference ---
    val theme: kotlinx.coroutines.flow.Flow<String> = repository.themePreference

    fun saveThemePreference(theme: String) {
        viewModelScope.launch {
            repository.saveThemePreference(theme)
        }
    }
}

class DeviceViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeviceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeviceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}