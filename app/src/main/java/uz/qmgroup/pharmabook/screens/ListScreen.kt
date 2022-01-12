package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.models.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Medicine>(), producer = {
        this.value = MedicinesRepo().getMedicines()?.toObjects(
            Medicine::class.java
        ) ?: emptyList()
        loading = false
    })
    if (loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        MedicinesList(
            modifier = modifier,
            list = list
        )
    }
}