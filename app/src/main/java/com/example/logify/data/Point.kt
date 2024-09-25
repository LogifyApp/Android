package com.example.logify.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "points",
    foreignKeys = [ForeignKey(
        entity = Cargo::class,
        parentColumns = ["id"],
        childColumns = ["cargoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Point(
    @PrimaryKey val id: Int,
    val label: String,
    val latitude: Double,
    val longitude: Double,
    val order: Int,
    val cargoId: Int
)