package com.example.logify.services

import com.example.logify.data.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface TokenService {
    @GET
    suspend fun getToken(@Body userId: Int): Response<Token>
}