package com.example.logify.services

import com.example.logify.data.User
import com.example.logify.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login")
    suspend fun login(@Body phoneNumber: String, passwordHashed: String): Response<User>

    @POST("/register")
    suspend fun register(@Body userDto: UserDto): Response<User>
}