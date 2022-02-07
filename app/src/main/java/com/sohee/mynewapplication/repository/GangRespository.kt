package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.GangDao
import com.sohee.mynewapplication.model.Gang
import kotlinx.coroutines.flow.Flow

class GangRespository (private val userDao: GangDao) {

    val readAllData : Flow<List<Gang>> = userDao.readAllData()

    suspend fun addUser(user: Gang){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Gang){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Gang){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Gang>> {
        return userDao.searchDatabase(searchQuery)
    }
}