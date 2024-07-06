package com.example.logify.data

data class User(var id: Int, var name: String, var surname: String, var passwordHashed: String, val token: String? = null){

}