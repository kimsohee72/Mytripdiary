package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.Chung1Dao
import com.sohee.mynewapplication.model.Chung1
import kotlinx.coroutines.flow.Flow

class Chung1Respository(private val userDao: Chung1Dao) {

    val readAllData : Flow<List<Chung1>> = userDao.readAllData()

    suspend fun addUser(user: Chung1){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Chung1){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Chung1){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Chung1>> {
        return userDao.searchDatabase(searchQuery)
    }
}