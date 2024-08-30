package com.example.logify.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.logify.data.Cargo
import com.example.logify.data.ChatLastOpened
import com.example.logify.data.Document
import com.example.logify.data.Token
import com.example.logify.data.User

@TypeConverters(Converters::class)
@Database(entities = [User::class, Token::class, Cargo::class, ChatLastOpened::class, Document::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tokenDao(): TokenDao
    abstract fun cargoDao(): CargoDao
    abstract fun chatLastOpenedDao(): ChatLastOpenedDao
    abstract fun documentDao(): DocumentDao
}