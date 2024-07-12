package com.example.logify.services

import com.example.logify.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageService @Inject constructor(private val repository: MessageRepository) {
    private val _unreadMessageCount = MutableStateFlow(0)
    val unreadMessageCount: StateFlow<Int> get() = _unreadMessageCount.asStateFlow()

    fun fetchUnreadMessageCount(chatId: Int, lastOpened: LocalDateTime) {
        CoroutineScope(Dispatchers.IO).launch {
            val count = repository.getUnreadMessageCount(chatId, lastOpened)
            _unreadMessageCount.update { count }
        }
    }
}