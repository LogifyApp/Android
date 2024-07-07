package com.example.logify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey var id: Int, var name: String, var surname: String, var phoneNumber: String, var role: String){

}