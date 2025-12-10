package com.d00223094.hostlockmobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeviceViewModel(private val repository: AppRepository) : ViewModel() {

    val accessLogs: StateFlow<List<AccessLog>> = repository.getAllAccessLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // Keep flow active for 5s
            initialValue = emptyList() // Start with an empty list
        )

    val guestList: StateFlow<List<GuestList>> = repository.getAllGuests()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val users: StateFlow<List<Users>> = repository.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // --- Functions to Modify Data ---

    fun addAccessLog(summary: String, details: String) {
        viewModelScope.launch {
            repository.insertAccessLog(AccessLog(summary = summary, details = details))
        }
    }

    fun addGuest(name: String, booking: String) {
        viewModelScope.launch {
            repository.insertGuest(GuestList(name = name, booking = booking))
        }
    }

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
