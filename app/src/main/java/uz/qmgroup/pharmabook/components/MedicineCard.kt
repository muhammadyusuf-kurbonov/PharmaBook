package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Copy
import uz.qmgroup.pharmabook.medicines.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(
    modifier: Modifier = Modifier,
    medicineModels: Medicine,
    editorEnabled: Boolean = false,
    onEdit: (Medicine) -> Unit = {},
    onCopy: (Medicine) -> Unit = {},
    onDelete: (Medicine) -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = medicineModels.name.trim(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = medicineModels.vendor,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            if (editorEnabled) {
                IconButton(onClick = { onEdit(medicineModels) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
                IconButton(onClick = { onCopy(medicineModels) }) {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        imageVector = FontAwesomeIcons.Solid.Copy,
                        contentDescription = null
                    )
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
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp),
                    text = "${medicineModels.positionRow} \u2022 ${medicineModels.positionColumn}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}