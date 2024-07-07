package com.example.logify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.User
import com.example.logify.dto.UserDto
import com.example.logify.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userDto = MutableStateFlow<UserDto?>(null)
    val userDto: StateFlow<UserDto?> get() = _userDto

    fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.login(phoneNumber, password)
            _userDto.value = result
        }
    }

    fun register(userDto: UserDto) {
        viewModelScope.launch {
            val result = userRepository.register(userDto)
            if (result) {
                _userDto.value = userDto
            }
        }
    }
}