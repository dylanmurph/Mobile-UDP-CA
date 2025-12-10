package com.d00223094.hostlockmobile.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d00223094.hostlockmobile.network.MarsApi
import com.d00223094.hostlockmobile.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photo: MarsPhoto) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getMarsPhotos()
                if (listResult.isNotEmpty()) {
                    MarsUiState.Success(listResult.random())
                } else {
                    MarsUiState.Error
                }
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: retrofit2.HttpException) {
                MarsUiState.Error
            }
        }
    }
}