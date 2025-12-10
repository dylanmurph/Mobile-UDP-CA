package com.d00223094.hostlockmobile.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
interface GuestListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGuest(guest: GuestList)

    @Update
    suspend fun updateGuest(guest: GuestList)

    @Delete
    suspend fun deleteGuest(guest: GuestList)

    @Query("SELECT * FROM guest_list WHERE id = :id")
    fun getGuestById(id: Int): Flow<GuestList>

    @Query("SELECT * FROM guest_list WHERE userId = :userId ORDER BY id DESC")
    fun getAllGuestsForUser(userId: Int): Flow<List<GuestList>>

    @Query("DELETE FROM guest_list WHERE id = :id")
    suspend fun deleteGuestById(id: Int)

    @Query("DELETE FROM guest_list WHERE userId = :userId")
    suspend fun deleteAllGuestsForUser(userId: Int)

    @Query("DELETE FROM guest_list")
    suspend fun deleteAllGuests()
}
    