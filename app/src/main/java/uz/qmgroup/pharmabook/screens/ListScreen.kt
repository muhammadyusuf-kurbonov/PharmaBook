package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.models.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import java.util.*

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    Column(modifier = modifier.padding(8.dp)) {
        var loading by remember {
            mutableStateOf(false)
        }

        var searchPattern by remember {
            mutableStateOf("")
        }

        val searchResults = remember {
            mutableStateListOf<Medicine>()
        }

        LaunchedEffect(key1 = Unit) {
            searchResults.addAll(MedicinesRepo().search(searchPattern))
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchPattern,
            onValueChange = { newValue ->
                searchPattern =
                    newValue.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            },
            placeholder = {
                Text(text = stringResource(R.string.search))
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    this.defaultKeyboardAction(ImeAction.Search)
                    scope.launch {
                        try {
                            loading = true
                            searchResults.clear()
                            searchResults.addAll(MedicinesRepo().search(searchPattern))
                        } finally {
                            loading = false
                        }
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            trailingIcon = {
                IconButton(onClick = {
                    scope.launch {
                        try {
                            loading = true
                            searchResults.clear()
                            searchResults.addAll(MedicinesRepo().search(searchPattern))
                        } finally {
                            loading = false
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        )

        if (loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            MedicinesList(
                modifier = Modifier.fillMaxWidth(),
                list = searchResults
            )
        }
    }
}