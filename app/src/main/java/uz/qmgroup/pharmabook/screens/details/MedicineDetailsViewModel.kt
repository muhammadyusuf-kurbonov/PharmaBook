package uz.qmgroup.pharmabook.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

class MedicineDetailsViewModel: ViewModel() {
    var medicine by mutableStateOf<Medicine?>(null)
    private set

    var isLoading by mutableStateOf(false)
    private set

    fun loadData(id: Long){
        viewModelScope.launch {
            try {
                isLoading = true
                medicine = MedicinesRepo().getMedicine(id)
            } finally {
                isLoading = false
            }
        }
    }
}