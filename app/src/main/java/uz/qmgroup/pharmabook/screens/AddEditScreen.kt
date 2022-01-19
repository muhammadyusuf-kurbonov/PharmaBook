package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.TagsField
import uz.qmgroup.pharmabook.models.Medicine
import java.util.*

@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onSave: (Medicine) -> Unit,
    onCancel: () -> Unit,
    medicine: Medicine? = null
) {
    var medicineName by remember { mutableStateOf("") }

    var medicineProducer by remember { mutableStateOf("") }

    val medicineTags = remember { mutableStateListOf<String>() }

    val saving = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = medicine) {
        medicine?.let {
            medicineName = it.name
            medicineProducer = it.producer
            medicineTags.addAll(it.tags)
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
                    val newMedicine = medicine?.copy(
                        name = medicineName.trim().replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        producer = medicineProducer.trim().replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        tags = medicineTags.toList()
                    )
                        ?: Medicine(
                            name = medicineName.trim().replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            producer = medicineProducer.trim().replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            tags = medicineTags.toList()
                        )
                    onSave(newMedicine)
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                enabled = medicineName.isNotEmpty() && medicineProducer.isNotEmpty() && !saving.value
            ) {
                Text(stringResource(medicine?.id?.let { R.string.Save } ?: R.string.add))
            }
        }
    }
}