package com.example.logify.model

import com.example.logify.dao.UserDao
import com.example.logify.data.User
import com.example.logify.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userService: UserService, private val userDao: UserDao) {

    // Simulated network call to login
//    suspend fun login(phoneNumber: String, password: String): User? {
//        return withContext(Dispatchers.IO) {
//            val response = userService.login(phoneNumber, password)
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    userDao.insert(it) // Save to local database
//                    return@let it
//                }
//            }
//            return@withContext null
//        }
//    }

    // Simulated network call to register
//    suspend fun register(user: User): Boolean {
//        return withContext(Dispatchers.IO) {
//            val response = userService.register(user)
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    userDao.insert(it) // Save to local database
//                    return@withContext true
//                }
//            }
//            return@withContext false
//        }
//    }
//
//    // Retrieve user data from local database
//    suspend fun getUser(phoneNumber: String): User? {
//        return withContext(Dispatchers.IO) {
//            return@withContext userDao.getUser(phoneNumber)
//        }
//    }
}