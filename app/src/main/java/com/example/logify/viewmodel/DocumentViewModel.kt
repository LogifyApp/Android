package com.example.logify.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.Document
import com.example.logify.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val repository: DocumentRepository
) : ViewModel() {

    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>> get() = _documents

    init {
        loadDocuments()
    }

    private fun loadDocuments() {
        viewModelScope.launch {
            _documents.value = repository.getDocuments()
        }
    }

    fun addDocument(name: String, uri: Uri) {
        viewModelScope.launch {
            repository.addDocument(name, uri)
            loadDocuments()
        }
    }

    fun removeDocument(document: Document) {
        viewModelScope.launch {
            repository.removeDocument(document)
            loadDocuments()
        }
    }
}