package com.example.logify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logify.data.Cargo
import com.example.logify.repository.CargoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CargoViewModel @Inject constructor(private val repository: CargoRepository): ViewModel(){
    private val _cargos = MutableLiveData<List<Cargo>>()
    val cargos: LiveData<List<Cargo>> get() = _cargos

    private val _selectedCargo = MutableLiveData<Cargo?>()
    val selectedCargo: LiveData<Cargo?> get() = _selectedCargo

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadCargos(employerId: Int) {
        viewModelScope.launch {
            try {
                val cargoList = repository.returnListOfCargos(employerId)
                _cargos.postValue(cargoList)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun loadCargosByDriver(employerId: Int, driverId: Int) {
        viewModelScope.launch {
            try {
                val cargoList = repository.returnListOfCargosByDriver(employerId, driverId)
                _cargos.postValue(cargoList)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun createCargo(cargo: Cargo) {
        viewModelScope.launch {
            try {
                val newCargo = repository.createCargo(cargo)
                val updatedCargos = _cargos.value.orEmpty() + newCargo
                _cargos.postValue(updatedCargos)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun updateDescriptionOfCargo(id: Int, description: String) {
        viewModelScope.launch {
            try {
                val updatedCargo = repository.updateDescriptionOfCargo(id, description)
                val updatedCargos = _cargos.value.orEmpty().map { if (it.id == id) updatedCargo else it }
                _cargos.postValue(updatedCargos)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun selectCargo(cargo: Cargo) {
        _selectedCargo.postValue(cargo)
    }

    fun clearSelection() {
        _selectedCargo.postValue(null)
    }

    fun clearError() {
        _error.postValue(null)
    }
}