package uz.qmgroup.pharmabook.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

class MedicineDetailsViewModel: ViewModel() {
    private val _medicine = MutableStateFlow<Medicine?>(null)
    val medicine = _medicine.asStateFlow()

    var isLoading by mutableStateOf(false)
    private set

    fun loadData(id: Long){
        viewModelScope.launch {
            try {
                isLoading = true
                _medicine.tryEmit(MedicinesRepo().getMedicine(id))
            } finally {
                isLoading = false
            }
        }
    }
}