package com.d00223094.hostlockmobile.data



import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "guest_list",
    foreignKeys = [ForeignKey(
        entity = Users::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GuestList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val booking: String,
    @androidx.room.ColumnInfo(index = true)
    val userId: Int
)