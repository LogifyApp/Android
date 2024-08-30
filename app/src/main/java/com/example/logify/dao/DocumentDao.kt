package com.example.logify.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.logify.data.Document

@Dao
interface DocumentDao {
    @Query("SELECT * FROM documents")
    suspend fun getAllDocuments(): List<Document>

    @Insert
    suspend fun insertDocument(document: Document)

    @Delete
    suspend fun deleteDocument(document: Document)
}