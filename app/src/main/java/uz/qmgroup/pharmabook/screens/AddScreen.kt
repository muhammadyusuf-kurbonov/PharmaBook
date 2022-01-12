package uz.qmgroup.pharmabook.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.TagsField
import uz.qmgroup.pharmabook.models.Medicine

@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    onAdd: (Medicine) -> Unit
) {
    var medicineName by remember { mutableStateOf("") }

    var medicineProducer by remember { mutableStateOf("") }

    val medicineTags = remember { mutableStateListOf<String>() }

    val adding = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text(stringResource(R.string.Name)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = medicineProducer,
            onValueChange = { medicineProducer = it },
            label = { Text(stringResource(R.string.Producer)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TagsField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            tags = medicineTags, addTag = {
            medicineTags.add(it)
        }, removeTag = {
            medicineTags.remove(it)
        })

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                adding.value = true
                val medicine = Medicine(
                    name = medicineName,
                    producer = medicineProducer,
                    tags = medicineTags.toList()
                )
                onAdd(medicine)
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp),
            enabled = medicineName.isNotEmpty() && medicineProducer.isNotEmpty() && !adding.value
        ) {
            Text(stringResource(R.string.add))
        }
    }
}