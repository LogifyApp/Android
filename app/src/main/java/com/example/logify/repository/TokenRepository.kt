package com.example.logify.repository

import com.example.logify.dao.TokenDao
import com.example.logify.data.Token
import com.example.logify.services.TokenService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(private val tokenService: TokenService, private val tokenDao: TokenDao) {

    fun insertToken(token: Token) {
        tokenDao.insertToken(token)
    }

    fun getTokenLocal(): Token? {
        return tokenDao.getToken()
    }

    suspend fun getToken(userId: Int): Token? {
        return withContext(Dispatchers.IO) {
            val response = tokenService.getToken(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let it
                }
            }
            return@withContext null
        }
    }
}