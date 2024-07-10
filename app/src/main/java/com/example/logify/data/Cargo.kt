package com.example.logify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Date

@Entity(tableName = "cargo")
data class Cargo(@PrimaryKey var id: Int, var status: String, var creationDate: String, var carId: Int, var driverId: Int) {

}