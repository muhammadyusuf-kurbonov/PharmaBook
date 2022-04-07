package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import java.util.*

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun AutoCompleteMultiSelect(
    modifier: Modifier = Modifier,
    allItems: List<String>,
    selected: List<String>,
    select: (String) -> Unit,
    deselect: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            selected.forEach {
                Chip(
                    onClick = { deselect(it) }, colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary
                    ), border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = it)
                }
            }
        }

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = query,
                onValueChange = { newQuery ->
                    query =
                        newQuery.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    expanded = true
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            val filteredItems = allItems.filter { !selected.contains(it) }
                .filter { it.contains(query, ignoreCase = true) }

            if (filteredItems.isNotEmpty()) {
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    filteredItems.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = { select(option) })
                    }
                }
            }
        }
    }
}