package com.example.logify.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.logify.data.Cargo

@Dao
interface CargoDao {
    @Query("SELECT * FROM cargos ORDER BY CASE WHEN status = 'Created' THEN 1 WHEN status = 'Started' THEN 2 WHEN status = 'In-check' THEN 3 WHEN status = 'Problem' THEN 4 ELSE 5 END")
    suspend fun returnListOfCargos(): List<Cargo>

    @Query("SELECT * FROM cargos WHERE driverId = :driverId ORDER BY CASE WHEN status = 'Created' THEN 1 WHEN status = 'Started' THEN 2 WHEN status = 'In-check' THEN 3 WHEN status = 'Problem' THEN 4 ELSE 5 END")
    suspend fun returnListOfCargosByDriver(driverId: Int): List<Cargo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCargo(cargo: Cargo)

    @Update
    suspend fun updateCargo(cargo: Cargo)

    @Query("UPDATE cargos SET description = :description WHERE id = :id")
    suspend fun updateDescriptionOfCargo(id: Int, description: String)
}