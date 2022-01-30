package uz.qmgroup.pharmabook.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

class ListViewModel: ViewModel() {
    var loading by mutableStateOf(false)
    private set

    val searchResults = mutableStateListOf<Medicine>()

    fun startSearch(searchPattern: String){
        viewModelScope.launch {
            try {
                loading = true
                searchResults.clear()
                searchResults.addAll(MedicinesRepo().search(searchPattern))
            }finally {
                loading = false
            }
        }
    }

}