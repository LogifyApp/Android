package com.example.logify.repository

import com.example.logify.dao.CargoDao
import com.example.logify.dao.PointDao
import com.example.logify.data.Cargo
import com.example.logify.data.Point
import com.example.logify.services.CargoService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CargoRepository @Inject constructor(private val cargoService: CargoService, private val cargoDao: CargoDao, private val pointDao: PointDao){
    suspend fun returnListOfCargos(employerId: Int): List<Cargo> {
        val cargos = cargoService.returnListOfCargos(employerId)
        for (cargo : Cargo in cargos){
            cargoDao.createCargo(cargo)
        }
        return cargos
    }

    suspend fun returnListOfCargosByDriver(employerId: Int, driverId: Int): List<Cargo> {
        val cargos = cargoService.returnListOfCargosByDriver(employerId, driverId)
        for (cargo : Cargo in cargos){
            cargoDao.createCargo(cargo)
        }
        return cargos
    }

    suspend fun createCargo(cargo: Cargo): Cargo {
        val newCargo = cargoService.createCargo(cargo)
        cargoDao.createCargo(newCargo)
        return newCargo
    }

    suspend fun updateDescriptionOfCargo(id: Int, description: String): Cargo {
        val updatedCargo = cargoService.updateDescriptionOfCargo(id, description)
        cargoDao.updateCargo(updatedCargo)
        return updatedCargo
    }

    fun getCargoById(id: Int): Flow<Cargo> {
        return cargoDao.getCargoById(id)
    }

    fun getPointsByCargoId(cargoId: Int): Flow<List<Point>> {
        return pointDao.getPointsByCargoId(cargoId)
    }
}