package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
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
    placeholder: String = "",
    items: List<String>,
    addItem: (String) -> Unit,
    deleteItem: (String) -> Unit
) {
    var newOption by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        items.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp),
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = newOption,
                placeholder = { Text(text = placeholder) },
                onValueChange = { newOption = it },
                textStyle = MaterialTheme.typography.body1
            )
            IconButton(onClick = { addItem(newOption); newOption = "" }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }

    }
}