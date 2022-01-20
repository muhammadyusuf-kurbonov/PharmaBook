package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.medicines.Medicine

@Composable
fun MedicinesList(
    modifier: Modifier = Modifier,
    list: List<Medicine>,
    editorEnabled: Boolean = false,
    onEdit: (Medicine) -> Unit = {},
    onDelete: (Medicine) -> Unit = {}
) {
    if (list.isNotEmpty())
        LazyColumn(modifier = modifier) {
            item {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(R.string.Medicines),
                    style = MaterialTheme.typography.subtitle1
                )
            }
            items(list) {
                MedicineCard(
                    modifier = Modifier.padding(4.dp),
                    medicineModels = it,
                    editorEnabled = editorEnabled,
                    onEdit = onEdit,
                    onDelete = onDelete
                )
            }
        }
    else
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.No_medicines),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        }
}

@Composable
fun MedicineCard(
    modifier: Modifier = Modifier,
    medicineModels: Medicine,
    editorEnabled: Boolean = false,
    onEdit: (Medicine) -> Unit = {},
    onDelete: (Medicine) -> Unit = {}
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medicineModels.name, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = medicineModels.vendor.uppercase(), style = MaterialTheme.typography.body2)
            }
            if (editorEnabled) {
                IconButton(onClick = { onEdit(medicineModels) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
                IconButton(onClick = {
                    onDelete(medicineModels)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}