package com.example.logify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.User
import com.example.logify.dto.UserDto
import com.example.logify.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun login(phoneNumber: String, password: String): User? {
        viewModelScope.launch {
            val result = userRepository.login(phoneNumber, password)
            _user.value = result
        }
        return _user.value
    }

    fun register(userDto: UserDto): User?{
        viewModelScope.launch {
            val result = userRepository.register(userDto)
            if (result != null) {
                _user.value = result
            }
        }
        return _user.value
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun getUser(callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUser()
            callback(user)
        }
    }
}