package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Jenla2
import kotlinx.coroutines.flow.Flow

@Dao
interface Jenla2Dao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Jenla2)

    @Insert
    suspend fun addUserDb(users : List<Jenla2>)

    @Update
    suspend fun updateUser(user : Jenla2)

    @Delete
    suspend fun deleteUser(user : Jenla2)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Jenla2>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Jenla2>>
}