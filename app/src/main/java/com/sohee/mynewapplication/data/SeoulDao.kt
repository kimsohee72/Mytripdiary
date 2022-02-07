package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Seoul
import kotlinx.coroutines.flow.Flow

@Dao
interface SeoulDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Seoul)

    @Insert
    suspend fun addUserDb(users : List<Seoul>)

    @Update
    suspend fun updateUser(user : Seoul)

    @Delete
    suspend fun deleteUser(user : Seoul)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Seoul>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Seoul>>
}