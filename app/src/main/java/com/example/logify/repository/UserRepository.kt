package com.example.logify.repository

import com.example.logify.dao.UserDao
import com.example.logify.data.User
import com.example.logify.dto.UserDto
import com.example.logify.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: UserService, private val userDao: UserDao) {

    suspend fun login(phoneNumber: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            val response = userService.login(phoneNumber, password)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let it
                }
            }
            return@withContext null
        }
    }

    suspend fun register(userDto: UserDto): User? {
        return withContext(Dispatchers.IO) {
            val response = userService.register(userDto)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let it
                }
            }
            return@withContext null
        }
    }

    fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun getUser(): User? {
        return userDao.getUser()
    }
}