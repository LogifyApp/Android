package com.example.logify.repository

import com.example.logify.dto.UserDto
import com.example.logify.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userService: UserService) {

//     Simulated network call to login
    suspend fun login(phoneNumber: String, password: String): UserDto? {
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

//     Simulated network call to register
    suspend fun register(userDto: UserDto): Boolean {
        return withContext(Dispatchers.IO) {
            val response = userService.register(userDto)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext true
                }
            }
            return@withContext false
        }
    }
}