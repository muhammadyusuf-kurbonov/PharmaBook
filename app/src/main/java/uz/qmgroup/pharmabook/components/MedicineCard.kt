package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.medicines.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(
    modifier: Modifier = Modifier,
    medicine: Medicine,
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
                    text = medicine.name.trim(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = medicine.vendor,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            if (editorEnabled) {
                EditControls(
                    onEdit = { onEdit(medicine) },
                    onCopy = { onCopy(medicine) },
                    onDelete = { onDelete(medicine) }
                )
            } else {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp),
                    text = "${medicine.positionRow} \u2022 ${medicine.positionColumn}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}