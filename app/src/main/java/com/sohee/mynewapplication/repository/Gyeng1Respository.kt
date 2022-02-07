package com.sohee.mynewapplication.repository


import com.sohee.mynewapplication.data.Gyeng1Dao
import com.sohee.mynewapplication.model.Gyeng1
import kotlinx.coroutines.flow.Flow

class Gyeng1Respository (private val userDao: Gyeng1Dao) {

    val readAllData : Flow<List<Gyeng1>> = userDao.readAllData()

    suspend fun addUser(user: Gyeng1){
        userDao.addUser(user)
    }

    suspend fun updateUser(user :Gyeng1){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : Gyeng1){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Gyeng1>> {
        return userDao.searchDatabase(searchQuery)
    }
}