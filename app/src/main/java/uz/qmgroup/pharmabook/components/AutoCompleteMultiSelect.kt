package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
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
                Chip(onClick = { deselect(it) }) {
                    Text(text = it)
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = { query = it }
        )

        DropdownMenu(expanded = query.isNotEmpty(), onDismissRequest = {}) {
            allItems.filter { !selected.contains(it) and it.contains(query) }.forEach {
                DropdownMenuItem(onClick = { select(it) }) {
                    Text(text = it)
                }
            }
        }
    }
}