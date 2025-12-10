package com.d00223094.hostlockmobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeviceViewModel(private val repository: AppRepository) : ViewModel() {
    private val _loggedInUserId = MutableStateFlow<Int?>(null)
    @OptIn(ExperimentalCoroutinesApi::class)
    val accessLogs: StateFlow<List<AccessLog>> = _loggedInUserId.flatMapLatest { userId ->
        if (userId != null) {
            repository.getAllAccessLogsForUser(userId)
        } else {
            flowOf(emptyList()) // If no user is logged in, return an empty list
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

    // 3. Create a function to be called after successful login
    fun onLoginSuccess(userId: Int) {
        _loggedInUserId.value = userId
    }

    // 4. Update functions to use the stored userId
    fun addAccessLog(summary: String, details: String) {
        _loggedInUserId.value?.let { userId ->
            viewModelScope.launch {
                repository.insertAccessLog(summary, details, userId)
            }
        }
    }

    fun addGuest(name: String, booking: String) {
        _loggedInUserId.value?.let { userId ->
            viewModelScope.launch {
                repository.insertGuest(name, booking, userId)
            }
        }
    }

    val users: StateFlow<List<Users>> = repository.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // --- Functions to Modify Data ---



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

    fun addUser(name: String, password: String, email: String) {
        viewModelScope.launch {
            repository.insertUser(Users(name = name, password = password, email = email))
        }
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
