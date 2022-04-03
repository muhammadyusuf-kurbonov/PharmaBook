package uz.qmgroup.pharmabook.screens.editor.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import java.util.*

class EditorHomeViewModel : ViewModel() {
    var loading by mutableStateOf(false)
        private set

    var searchPattern by mutableStateOf("")
        private set

    val list = mutableStateListOf<Medicine>()

    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch {
            MedicinesRepo().deleteMedicine(medicine = medicine)
            startSearch()
        }
    }

    fun duplicateMedicine(medicine: Medicine) {
        viewModelScope.launch {
            MedicinesRepo().addMedicine(medicine.copy(
                id = 0,
                name = medicine.name + "(2)"
            ))
            startSearch()
        }
    }

    fun updateSearchPattern(searchPattern: String) {
        this.searchPattern = searchPattern.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        startSearch()
    }

    fun startSearch() {
        viewModelScope.launch {
            loading = true
            list.clear()
            list.addAll(MedicinesRepo().searchMedicineByName(searchPattern))
            loading = false
        }
    }
}