package com.example.logify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "cars")
data class Car(@PrimaryKey var number: String, var model: String, var brand: String, var status: Boolean, var employerId: Int) {

}