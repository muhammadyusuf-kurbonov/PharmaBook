package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.medicines.Medicine

@Composable
fun MedicinesList(
    modifier: Modifier = Modifier,
    list: List<Medicine>,
    loading: Boolean = false,
    editorEnabled: Boolean = false,
    onEdit: (Medicine) -> Unit = {},
    onDelete: (Medicine) -> Unit = {},
    onCLick: (Medicine) -> Unit = {},
    onCopy: (Medicine) -> Unit = {}
) {
    Column {
        if (loading) {
            LinearProgressIndicator(
                modifier.fillMaxWidth()
            )
        } else {
            if (list.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(0.dp, 8.dp)
                ) {
                    items(list) {
                        MedicineCard(
                            modifier = Modifier
                                .padding(4.dp, 2.dp)
                                .clickable { onCLick(it) },
                            medicineModels = it,
                            editorEnabled = editorEnabled,
                            onEdit = onEdit,
                            onDelete = onDelete,
                            onCopy = onCopy
                        )
                    }
                }
            } else
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.No_medicines),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6
                    )
                }
        }
    }
}
