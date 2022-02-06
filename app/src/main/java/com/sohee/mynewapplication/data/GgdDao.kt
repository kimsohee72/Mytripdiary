package com.sohee.mynewapplication.data

import androidx.room.*
import com.sohee.mynewapplication.model.Ggd
import kotlinx.coroutines.flow.Flow

@Dao
interface GgdDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : Ggd)

    @Insert
    suspend fun addUserDb(users : List<Ggd>)

    @Update
    suspend fun updateUser(user : Ggd)

    @Delete
    suspend fun deleteUser(user : Ggd)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : Flow<List<Ggd>>

    @Query("SELECT * FROM user_table WHERE place LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Ggd>>
}