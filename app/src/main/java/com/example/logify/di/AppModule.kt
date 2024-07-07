package com.example.logify.di

import android.content.Context
import androidx.room.Room
import com.example.logify.dao.AppDatabase
import com.example.logify.repository.UserRepository
import com.example.logify.services.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private const val BASE_URL = "https://yourapi.com"

    private fun provideUserService(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }

    private fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

//    fun provideUserRepository(context: Context): UserRepository {
//        val userService = provideUserService()
//        val database = provideDatabase(context)
//        return UserRepository(userService, database.userDao())
//    }
}