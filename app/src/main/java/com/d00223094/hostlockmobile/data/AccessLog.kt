package com.d00223094.hostlockmobile.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "access_logs",
    foreignKeys = [ForeignKey(
        entity = Users::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AccessLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val property: String,
    val fobNumber: String,
    val status: String,
    val date: String,
    @androidx.room.ColumnInfo(index = true)
    val userId: Int
)