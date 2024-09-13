package com.example.logify.repository

import com.example.logify.dao.CargoDao
import com.example.logify.data.Cargo
import com.example.logify.services.CargoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CargoRepository @Inject constructor(private val cargoService: CargoService, private val cargoDao: CargoDao){
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
}