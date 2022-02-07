package com.sohee.mynewapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Gyeng2 (
    // autoGenerate = true , 자동으로 PrimaryKey 생성해줌
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val place : String,
    val feeling : String
)