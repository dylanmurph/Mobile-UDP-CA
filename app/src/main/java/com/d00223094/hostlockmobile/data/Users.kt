package com.d00223094.hostlockmobile.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users (
    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,
    val name: String,
    val password: String,
    val email: String
)

