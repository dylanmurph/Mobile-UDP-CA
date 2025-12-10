package com.d00223094.hostlockmobile.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_logs")
data class AccessLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val summary: String,
    val details: String
)