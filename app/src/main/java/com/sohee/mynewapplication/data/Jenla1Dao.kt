package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Jenla1
import kotlinx.coroutines.flow.Flow

@Dao
interface Jenla1Dao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Jenla1)

    @Insert
    suspend fun addUserDb(users : List<Jenla1>)

    @Update
    suspend fun updateUser(user : Jenla1)

    @Delete
    suspend fun deleteUser(user : Jenla1)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Jenla1>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Jenla1>>
}