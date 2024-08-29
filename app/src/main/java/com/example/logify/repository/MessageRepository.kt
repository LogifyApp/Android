package com.example.logify.repository

import com.example.logify.dao.ChatLastOpenedDao
import com.example.logify.data.ChatLastOpened
import com.example.logify.services.ApiMessageService
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(private val apiMessageService: ApiMessageService, private val chatLastOpenedDao: ChatLastOpenedDao) {
    suspend fun getUnreadMessageCount(chatId: Int, lastOpened: LocalDateTime): Int {
        return apiMessageService.getUnreadMessageCount(chatId, lastOpened)
        //TODO redo calculation based on endpoint logic
    }

    suspend fun getLastOpened(chatId: Int): LocalDateTime? {
        return chatLastOpenedDao.getLastOpened(chatId)
    }

    suspend fun updateLastOpened(chatId: Int, lastOpened: LocalDateTime) {
        chatLastOpenedDao.insertOrUpdate(ChatLastOpened(chatId, lastOpened))
    }
}