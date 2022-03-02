package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.medicines.Medicine

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
            modifier = Modifier.padding(8.dp),
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(text = medicineModels.name, style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
                Text(text = medicineModels.vendor, style = MaterialTheme.typography.subtitle2)
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
            } else {
                Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(text = "Row " + medicineModels.positionRow, style = MaterialTheme.typography.body2)
                    Text(text = "Column " + medicineModels.positionColumn, style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}