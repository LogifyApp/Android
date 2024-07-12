package com.example.logify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "chat_last_opened")
data class ChatLastOpened(
    @PrimaryKey val chatId: Int,
    val lastOpened: LocalDateTime
)