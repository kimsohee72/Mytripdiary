package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.Jenla2Dao
import com.sohee.mynewapplication.model.Jenla2
import kotlinx.coroutines.flow.Flow

class Jenla2Respository (private val userDao: Jenla2Dao) {

    val readAllData : Flow<List<Jenla2>> = userDao.readAllData()

    suspend fun addUser(user: Jenla2){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Jenla2){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Jenla2){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Jenla2>> {
        return userDao.searchDatabase(searchQuery)
    }
}