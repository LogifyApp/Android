package com.example.logify.repository

import android.content.Context
import android.net.Uri
import com.example.logify.dao.DocumentDao
import com.example.logify.data.Document
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRepository @Inject constructor(
    private val context: Context,
    private val documentDao: DocumentDao
){

    suspend fun getDocuments(): List<Document> {
        return documentDao.getAllDocuments()
    }

    suspend fun addDocument(name: String, uri: Uri) {
        val newDocument = Document(id = UUID.randomUUID().toString(), name = name, uri = uri)
        documentDao.insertDocument(newDocument)
    }

    suspend fun removeDocument(document: Document) {
        documentDao.deleteDocument(document)
    }
}