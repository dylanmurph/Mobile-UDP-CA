package com.d00223094.hostlockmobile.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccessLogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLog(log: AccessLog)

    @Update
    suspend fun updateLog(log: AccessLog)

    @Delete
    suspend fun deleteLog(log: AccessLog)

    @Query("SELECT * FROM access_logs WHERE id = :id")
    fun getLogById(id: Int): Flow<AccessLog>

    @Query("SELECT * FROM access_logs WHERE userId = :userId ORDER BY id DESC")
    fun getAllLogsForUser(userId: Int): Flow<List<AccessLog>>

    @Query("DELETE FROM access_logs WHERE id = :id")
    suspend fun deleteLogById(id: Int)

    @Query("DELETE FROM access_logs WHERE userId = :userId")
    suspend fun deleteAllLogsForUser(userId: Int)

    @Query("DELETE FROM access_logs")
    suspend fun deleteAllLogs()
}