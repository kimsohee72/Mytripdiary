package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Gyeng2
import kotlinx.coroutines.flow.Flow

@Dao
interface Gyeng2Dao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Gyeng2)

    @Insert
    suspend fun addUserDb(users : List<Gyeng2>)

    @Update
    suspend fun updateUser(user : Gyeng2)

    @Delete
    suspend fun deleteUser(user : Gyeng2)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Gyeng2>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Gyeng2>>
}