package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsList(
    modifier: Modifier = Modifier,
    items: List<String>,
    addItem: (String) -> Unit,
    deleteItem: (String) -> Unit
) {
    var newOption by remember { mutableStateOf("") }

    LazyColumn(modifier = modifier) {
        items(items) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = it, style = MaterialTheme.typography.body1)
                IconButton(
                    onClick = { deleteItem(it) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = newOption,
                    onValueChange = { newOption = it },
                    textStyle = MaterialTheme.typography.body1
                )
                IconButton(onClick = { addItem(newOption); newOption = "" }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }
        }
    }
}