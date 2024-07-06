package com.example.logify.dao

data class UserDao(var name: String, var surname: String, var passwordHashed: String, val token: String? = null){

}