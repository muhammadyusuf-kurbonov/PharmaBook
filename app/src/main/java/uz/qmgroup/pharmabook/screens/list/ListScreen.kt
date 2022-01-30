package uz.qmgroup.pharmabook.screens.list

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
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import java.util.*

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel()
) {
    var searchPattern by remember { mutableStateOf("") }

    LaunchedEffect(key1 = searchPattern) {
        if (viewModel.loading)
            return@LaunchedEffect
        viewModel.startSearch(searchPattern)
    }

    Column(modifier = modifier.padding(8.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchPattern,
            onValueChange = { newValue ->
                searchPattern =
                    newValue.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            },
            placeholder = { Text(text = stringResource(R.string.search)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    this.defaultKeyboardAction(ImeAction.Search)
                    viewModel.startSearch(searchPattern)
                }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.startSearch(searchPattern)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        )

        MedicinesList(
            modifier = Modifier.fillMaxWidth(),
            list = viewModel.searchResults,
            loading = viewModel.loading,
            onCLick = {
                // TODO: Open details screen
            }
        )
    }
}