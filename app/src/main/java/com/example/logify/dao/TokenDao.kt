package com.example.logify.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.logify.data.Token

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: Token)

    @Query("SELECT * FROM token ORDER BY id DESC LIMIT 1")
    fun getToken(): Token?
}