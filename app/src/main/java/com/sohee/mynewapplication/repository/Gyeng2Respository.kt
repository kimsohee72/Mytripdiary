package com.sohee.mynewapplication.repository

import com.sohee.mynewapplication.data.Gyeng2Dao
import com.sohee.mynewapplication.model.Gyeng2
import kotlinx.coroutines.flow.Flow

class Gyeng2Respository (private val userDao: Gyeng2Dao) {

    val readAllData : Flow<List<Gyeng2>> = userDao.readAllData()

    suspend fun addUser(user: Gyeng2){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Gyeng2){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Gyeng2){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Gyeng2>> {
        return userDao.searchDatabase(searchQuery)
    }
}