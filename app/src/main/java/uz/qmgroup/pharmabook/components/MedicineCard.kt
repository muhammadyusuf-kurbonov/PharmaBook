package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medicineModels.name, style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(medicineModels.vendor)
                    }
                    append("\t")
                    append(medicineModels.positionRow.toString() + " / " + medicineModels.positionColumn.toString())
                }, style = MaterialTheme.typography.body2)
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