package com.example.logify.data

import androidx.compose.ui.semantics.Role
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey var id: Int, var name: String, var surname: String, var phoneNumber: String, var role: String ,var passwordHashed: String, val token: String? = null){

}