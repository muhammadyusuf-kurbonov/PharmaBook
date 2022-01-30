package uz.qmgroup.pharmabook.screens.editor.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

class EditorHomeViewModel: ViewModel() {
    var loading by mutableStateOf(false)
    private set

    val list = mutableStateListOf<Medicine>()

    fun deleteMedicine(medicine: Medicine){
        viewModelScope.launch {
            MedicinesRepo().deleteMedicine(medicine = medicine)
            reloadData()
        }
    }

    fun reloadData(){
        viewModelScope.launch {
            loading = true
            list.clear()
            list.addAll(MedicinesRepo().getMedicines())
            loading = false
        }
    }
}