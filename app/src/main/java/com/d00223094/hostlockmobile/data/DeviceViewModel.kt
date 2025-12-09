package com.d00223094.hostlockmobile.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeviceViewModel : ViewModel() {
    private val repository = AppRepository()

    private val _accessLogs = MutableStateFlow<List<AccessLog>>(emptyList())
    val accessLogs: StateFlow<List<AccessLog>> = _accessLogs.asStateFlow()

    private val _guestList = MutableStateFlow<List<GuestList>>(emptyList())
    val guestList: StateFlow<List<GuestList>> = _guestList.asStateFlow()

    init {
        loadAccessLogs()
        loadGuestList()
    }

    private fun loadAccessLogs() {
        _accessLogs.value = repository.loadAccessLogs()
    }

    private fun loadGuestList() {
        _guestList.value = repository.loadGuestList()
    }
}