package com.example.logify.services

import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime

interface ApiMessageService {

    @GET("path/to/unreadMessageCount")
    suspend fun getUnreadMessageCount(
        @Query("chatId") chatId: Int,
        @Query("lastOpened") lastOpened: LocalDateTime
    ): Int

    //TODO redo endpoint logic
}