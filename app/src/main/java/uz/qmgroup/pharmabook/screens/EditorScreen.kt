package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

@Composable
fun EditorScreen(
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var currentPath by remember { mutableStateOf("") }
    var medicine by remember { mutableStateOf<Medicine?>(null) }

    if (currentPath.isEmpty()) {
        Box(modifier = modifier) {
            var loading by remember {
                mutableStateOf(true)
            }

            var updateController by remember {
                mutableStateOf(0)
            }

            val list by produceState(initialValue = emptyList<Medicine>(), producer = {
                this.value = MedicinesRepo().getMedicines()
                loading = false
            }, key1 = updateController)

            if (loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                MedicinesList(
                    modifier = modifier,
                    list = list,
                    editorEnabled = true,
                    onDelete = {
                        scope.launch {
                            loading = true
                            MedicinesRepo().deleteMedicine(it)
                            updateController++
                        }
                    },
                    onEdit = {
                        medicine = it
                        currentPath = "edit"
                    },
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { currentPath = "add" }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    } else if (currentPath == "add") {
        AddEditScreen(
            modifier = modifier,
            onSave = {
                scope.launch {
                    MedicinesRepo()
                        .addMedicine(it)

                    currentPath = ""
                }
            },
            onCancel = {
                currentPath = ""
            }
        )
    } else if (currentPath == "edit") {
        AddEditScreen(
            modifier = modifier,
            onSave = {
                scope.launch {
                    MedicinesRepo().updateMedicine(it)

                    currentPath = ""
                }
            },
            onCancel = {
                currentPath = ""
            },
            medicineId = medicine?.id
        )
    }
}