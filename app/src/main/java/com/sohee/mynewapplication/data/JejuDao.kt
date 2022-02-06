package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Jeju
import kotlinx.coroutines.flow.Flow

@Dao
interface JejuDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Jeju)

    @Insert
    suspend fun addUserDb(users : List<Jeju>)

    @Update
    suspend fun updateUser(user : Jeju)

    @Delete
    suspend fun deleteUser(user : Jeju)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Jeju>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Jeju>>
}