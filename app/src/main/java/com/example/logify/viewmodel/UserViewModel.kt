package com.example.logify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.User
import com.example.logify.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

//    fun login(phoneNumber: String, password: String) {
//        viewModelScope.launch {
//            val result = userRepository.login(phoneNumber, password)
//            _user.value = result
//        }
//    }
//
//    fun register(user: User) {
//        viewModelScope.launch {
//            val result = userRepository.register(user)
//            _user.value = result
//        }
//    }
}