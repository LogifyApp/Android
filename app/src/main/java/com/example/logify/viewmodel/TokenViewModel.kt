package com.example.logify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.Token
import com.example.logify.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(private val tokenRepository: TokenRepository) : ViewModel() {
    fun insertToken(token: Token) {
        viewModelScope.launch {
            tokenRepository.insertToken(token)
        }
    }

    fun getToken(callback: (Token?) -> Unit) {
        viewModelScope.launch {
            val token = tokenRepository.getTokenLocal()
            callback(token)
        }
    }
}