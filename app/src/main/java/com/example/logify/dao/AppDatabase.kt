package com.example.logify.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.logify.data.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
}