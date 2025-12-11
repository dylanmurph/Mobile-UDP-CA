package com.d00223094.hostlockmobile.data

import kotlinx.coroutines.flow.Flow

// The repository now takes the DAOs as constructor parameters.
// This is called "dependency injection".
class AppRepository(
    private val accessLogDao: AccessLogDao,
    private val guestListDao: GuestListDao,
    private val usersDao: UsersDao,
    private val userDataStore: UserDataStore
) {

    // --- AccessLog Functions ---

    fun getAllAccessLogsForUser(userId: Int): Flow<List<AccessLog>> = accessLogDao.getAllLogsForUser(userId)
    fun getAllGuestsForUser(userId: Int): Flow<List<GuestList>> = guestListDao.getAllGuestsForUser(userId)

    suspend fun deleteAllAccessLogsForUser(userId: Int) = accessLogDao.deleteAllLogsForUser(userId)
    suspend fun deleteAllGuestsForUser(userId: Int) = guestListDao.deleteAllGuestsForUser(userId)



    suspend fun insertAccessLog(property: String, fobNumber: String, status: String, date: String, userId: Int) {
        accessLogDao.insertLog(AccessLog(
            property = property,
            fobNumber = fobNumber,
            status = status,
            date = date,
            userId = userId))
    }
    suspend fun insertGuest(name: String, email: String, property: String, fobNumber: String, userId: Int) {
        guestListDao.insertGuest(GuestList(
            name = name,
            email = email,
            property = property,
            fobNumber = fobNumber,
            userId = userId))
    }

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

    // --- Users Functions ---

    fun getAllUsers(): Flow<List<Users>> = usersDao.getAllUsers()

    fun getUserById(id: Int): Flow<Users> = usersDao.getUserById(id)

    suspend fun insertUser(user: Users): Long {
        return usersDao.insertUser(user)
    }

    suspend fun deleteAllUsers() {
        usersDao.deleteAllUsers()
    }

    suspend fun deleteUserById(id: Int) {
        usersDao.deleteUserById(id)
    }


    suspend fun getUserByName(username: String): Users? {
        return usersDao.getUserByName(username)
    }

    val themePreference: Flow<String> = userDataStore.themePreference

    suspend fun saveThemePreference(theme: String) {
        userDataStore.saveThemePreference(theme)
    }


}