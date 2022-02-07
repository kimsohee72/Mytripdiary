package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.Chung2Dao
import com.sohee.mynewapplication.model.Chung2
import kotlinx.coroutines.flow.Flow

class Chung2Respository (private val userDao: Chung2Dao) {

    val readAllData : Flow<List<Chung2>> = userDao.readAllData()

    suspend fun addUser(user: Chung2){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Chung2){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Chung2){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Chung2>> {
        return userDao.searchDatabase(searchQuery)
    }
}