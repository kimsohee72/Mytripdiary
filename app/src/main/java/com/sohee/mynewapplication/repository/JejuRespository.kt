package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.JejuDao
import com.sohee.mynewapplication.model.Jeju
import kotlinx.coroutines.flow.Flow

class JejuRepository(private val userDao: JejuDao) {

    val readAllData : Flow<List<Jeju>> = userDao.readAllData()

    suspend fun addUser(user: Jeju){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Jeju){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Jeju){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Jeju>> {
        return userDao.searchDatabase(searchQuery)
    }
}