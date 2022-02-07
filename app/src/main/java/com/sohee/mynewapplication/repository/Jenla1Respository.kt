package com.sohee.mynewapplication.repository

import com.sohee.mynewapplication.data.Jenla1Dao
import com.sohee.mynewapplication.model.Jenla1
import kotlinx.coroutines.flow.Flow

class Jenla1Respository (private val userDao: Jenla1Dao) {

    val readAllData : Flow<List<Jenla1>> = userDao.readAllData()

    suspend fun addUser(user: Jenla1){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Jenla1){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Jenla1){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Jenla1>> {
        return userDao.searchDatabase(searchQuery)
    }
}