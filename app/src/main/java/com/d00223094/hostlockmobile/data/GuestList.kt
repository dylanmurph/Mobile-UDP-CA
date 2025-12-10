package com.d00223094.hostlockmobile.data



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest_list")
data class GuestList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val booking: String
)