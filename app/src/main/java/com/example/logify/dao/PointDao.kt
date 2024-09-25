package com.example.logify.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.logify.data.Point
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {

    @Query("SELECT * FROM points WHERE cargoId = :cargoId ORDER BY `order`")
    fun getPointsByCargoId(cargoId: Int): Flow<List<Point>>
}