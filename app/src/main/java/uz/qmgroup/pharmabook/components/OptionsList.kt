package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
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

    fun commit() {
        if (newOption.isEmpty()) return
        addItem(newOption)
        newOption = ""
    }

    Column(modifier = modifier) {
        items.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
                IconButton(
                    onClick = { deleteItem(it) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.error
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
            TextField(
                modifier = Modifier.weight(1f),
                value = newOption,
                placeholder = { Text(text = placeholder) },
                onValueChange = { newOption = it },
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { commit() }
                )
            )
            IconButton(onClick = { commit() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }

    }
}