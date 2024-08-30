package com.example.logify.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class Document(
    @PrimaryKey val id: String,
    val name: String,
    val uri: Uri
)