package com.example.logify.services

import com.example.logify.data.Cargo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CargoService {
    @GET("cargos")
    suspend fun returnListOfCargos(@Query("employerId") employerId: Int): List<Cargo>

    @GET("cargos/driver")
    suspend fun returnListOfCargosByDriver(
        @Query("employerId") employerId: Int,
        @Query("driverId") driverId: Int
    ): List<Cargo>

    @POST("cargos")
    suspend fun createCargo(@Body cargo: Cargo): Cargo

    @POST("cargos/{id}/description")
    suspend fun updateDescriptionOfCargo(
        @Path("id") id: Int,
        @Body description: String
    ): Cargo
}