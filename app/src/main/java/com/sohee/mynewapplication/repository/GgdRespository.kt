package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.GgdDao
import com.sohee.mynewapplication.model.Ggd
import kotlinx.coroutines.flow.Flow

class GgdRespository (private val userDao: GgdDao) {

    val readAllData : Flow<List<Ggd>> = userDao.readAllData()

    suspend fun addUser(user: Ggd){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : Ggd){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Ggd){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Ggd>> {
        return userDao.searchDatabase(searchQuery)
    }
}