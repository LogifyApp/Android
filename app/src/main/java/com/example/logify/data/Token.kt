package com.example.logify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class Token(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val token: String,
    val expiryTime: Long
)