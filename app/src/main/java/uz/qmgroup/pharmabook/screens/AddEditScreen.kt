package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.TagsField
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import uz.qmgroup.pharmabook.tags.Tag
import java.util.*

@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onSave: (Medicine) -> Unit,
    onCancel: () -> Unit,
    medicineId: Long? = null
) {
    var medicineName by remember { mutableStateOf("") }

    var medicineProducer by remember { mutableStateOf("") }

    var medicinePosition by remember { mutableStateOf(0 to 0) }

    val medicineTags = remember { mutableStateListOf<Tag>() }

    val saving = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = medicineId) {
        medicineId?.let {
            val medicine = MedicinesRepo().getMedicine(medicineId)
            medicine?.let {
                medicineName = it.name
                medicineProducer = it.vendor
                medicinePosition = it.positionColumn to it.positionRow
                medicineTags.addAll(it.tags ?: emptyList())
            }
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text(stringResource(R.string.Name)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = medicineProducer,
            onValueChange = { medicineProducer = it },
            label = { Text(stringResource(R.string.Producer)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = medicinePosition.first.toString(),
                onValueChange = { medicinePosition = it.toInt() to medicinePosition.second },
                label = { Text(stringResource(R.string.Column)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Spacer(modifier = Modifier.width(4.dp))

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = medicinePosition.second.toString(),
                onValueChange = { medicinePosition = medicinePosition.first to it.toInt() },
                label = { Text(stringResource(R.string.Row)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TagsField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            tags = medicineTags, addTag = {
                medicineTags.add(it)
            }, removeTag = {
                medicineTags.remove(it)
            })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                enabled = medicineName.isNotEmpty() && medicineProducer.isNotEmpty() && !saving.value
            ) {
                Text(stringResource(android.R.string.cancel))
            }

            Button(
                onClick = {
                    saving.value = true
                    val newMedicineModels = Medicine(
                        id = medicineId ?: 0,
                        name = medicineName.formatForAppDatabase(),
                        vendor = medicineProducer.formatForAppDatabase(),
                        positionColumn = medicinePosition.first,
                        positionRow = medicinePosition.second,
                        tags = medicineTags.toList()
                    )
                    onSave(newMedicineModels)
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                enabled = medicineName.isNotEmpty() && medicineProducer.isNotEmpty() && !saving.value
            ) {
                Text(stringResource(medicineId?.let { R.string.Save } ?: R.string.add))
            }
        }
    }
}

fun String.formatForAppDatabase() = trim().replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}