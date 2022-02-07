package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Chung1
import kotlinx.coroutines.flow.Flow

@Dao
interface Chung1Dao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Chung1)

    @Insert
    suspend fun addUserDb(users : List<Chung1>)

    @Update
    suspend fun updateUser(user : Chung1)

    @Delete
    suspend fun deleteUser(user : Chung1)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Chung1>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Chung1>>
}