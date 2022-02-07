package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Gang
import kotlinx.coroutines.flow.Flow

@Dao
interface GangDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Gang)

    @Insert
    suspend fun addUserDb(users : List<Gang>)

    @Update
    suspend fun updateUser(user : Gang)

    @Delete
    suspend fun deleteUser(user : Gang)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Gang>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Gang>>
}