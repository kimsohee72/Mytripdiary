package com.sohee.mynewapplication.repository

import com.sohee.mynewapplication.data.SeoulDao
import com.sohee.mynewapplication.model.Seoul
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: SeoulDao) {

    val readAllData : Flow<List<Seoul>> = userDao.readAllData()

    suspend fun addUser(user: Seoul){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Seoul){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Seoul){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Seoul>> {
        return userDao.searchDatabase(searchQuery)
    }
}