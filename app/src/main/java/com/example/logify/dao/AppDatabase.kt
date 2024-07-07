package com.example.logify.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.logify.data.Token
import com.example.logify.data.User

@Database(entities = [User::class, Token::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tokenDao(): TokenDao
}