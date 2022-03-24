package uz.qmgroup.pharmabook.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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
                val value = MedicinesRepo().getMedicine(id)
                if (value != null) {
                    _medicine.tryEmit(value)
                    val alternatives = value.alternativeIds.map {
                        async {
                            MedicinesRepo().getMedicine(it)
                        }
                    }.awaitAll().filterNotNull()
                    _medicine.tryEmit(value.copy(
                        alternatives = alternatives
                    ))
                }
            } finally {
                isLoading = false
            }
        }
    }
}