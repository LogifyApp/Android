package com.example.logify.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.logify.data.ChatLastOpened
import java.time.LocalDateTime

@Dao
interface ChatLastOpenedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(chatLastOpened: ChatLastOpened)

    @Query("SELECT lastOpened FROM chat_last_opened WHERE chatId = :chatId LIMIT 1")
    suspend fun getLastOpened(chatId: Int): LocalDateTime?
}