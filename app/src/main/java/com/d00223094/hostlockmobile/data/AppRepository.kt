package com.d00223094.hostlockmobile.data

import kotlinx.coroutines.flow.Flow

// The repository now takes the DAOs as constructor parameters.
// This is called "dependency injection".
class AppRepository(
    private val accessLogDao: AccessLogDao,
    private val guestListDao: GuestListDao
) {

    // --- AccessLog Functions ---

    fun getAllAccessLogs(): Flow<List<AccessLog>> = accessLogDao.getAllLogs()

    fun getAccessLogById(id: Int): Flow<AccessLog> = accessLogDao.getLogById(id)

    suspend fun insertAccessLog(log: AccessLog) {
        accessLogDao.insertLog(log)
    }

    suspend fun deleteAllAccessLogs() {
        accessLogDao.deleteAllLogs()
    }

    suspend fun deleteAccessLogById(id: Int) {
        accessLogDao.deleteLogById(id)
    }


    // --- GuestList Functions ---

    fun getAllGuests(): Flow<List<GuestList>> = guestListDao.getAllGuests()

    fun getGuestById(id: Int): Flow<GuestList> = guestListDao.getGuestById(id)

    suspend fun insertGuest(guest: GuestList) {
        guestListDao.insertGuest(guest)
    }

    suspend fun deleteAllGuests() {
        guestListDao.deleteAllGuests()
    }

    suspend fun deleteGuestById(id: Int) {
        guestListDao.deleteGuestById(id)
    }
}
