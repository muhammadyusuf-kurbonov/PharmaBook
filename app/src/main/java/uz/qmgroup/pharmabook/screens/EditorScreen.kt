package uz.qmgroup.pharmabook.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.models.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo

@Composable
fun EditorScreen(
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var addScreenOpen by remember { mutableStateOf(false) }

    if (!addScreenOpen) {
        Box(modifier = modifier) {
            var loading by remember {
                mutableStateOf(true)
            }

            var updateController by remember {
                mutableStateOf(0)
            }

            val list by produceState(initialValue = emptyList<Medicine>(), producer = {
                this.value = MedicinesRepo().getMedicines()?.toObjects(
                    Medicine::class.java
                ) ?: emptyList()
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
                            MedicinesRepo().deleteMedicine(it.id)
                            updateController++
                        }
                    },
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { addScreenOpen = true }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    } else {

        val coroutineScope = rememberCoroutineScope()

        AddScreen(
            modifier = modifier,
            onAdd = {
                coroutineScope.launch {
                    MedicinesRepo(FirebaseFirestore.getInstance())
                        .addMedicine(it)

                    addScreenOpen = false
                }
            }
        )
    }
}