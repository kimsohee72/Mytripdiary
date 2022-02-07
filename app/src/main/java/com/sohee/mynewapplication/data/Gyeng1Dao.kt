package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Gyeng1
import kotlinx.coroutines.flow.Flow

@Dao
interface Gyeng1Dao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Gyeng1)

    @Insert
    suspend fun addUserDb(users : List<Gyeng1>)

    @Update
    suspend fun updateUser(user : Gyeng1)

    @Delete
    suspend fun deleteUser(user : Gyeng1)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Gyeng1>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Gyeng1>>
}