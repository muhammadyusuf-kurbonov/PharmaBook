package uz.qmgroup.pharmabook.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.screens.destinations.MedicineDetailsScreenDestination
import java.util.*

@Destination(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = viewModel()
) {
    var searchPattern by remember { mutableStateOf("") }

    LaunchedEffect(key1 = searchPattern) {
        if (viewModel.loading)
            return@LaunchedEffect
        viewModel.startSearch(searchPattern)
    }

    Column(modifier = modifier.padding(8.dp)) {
        Box {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchPattern,
                onValueChange = { newValue ->
                    searchPattern =
                        newValue.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                },
                placeholder = { Text(text = stringResource(R.string.search), style = MaterialTheme.typography.body2) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        this.defaultKeyboardAction(ImeAction.Search)
                        viewModel.startSearch(searchPattern)
                    }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier.wrapContentHeight()
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(99.dp),
                textStyle = MaterialTheme.typography.body2
            )

            DropdownMenu(expanded = viewModel.tagSearching, onDismissRequest = {}) {

            }
        }

        MedicinesList(
            modifier = Modifier.fillMaxWidth(),
            list = viewModel.searchResults,
            loading = viewModel.loading,
            onCLick = {
                navigator.navigate(MedicineDetailsScreenDestination(it.id))
            }
        )
    }
}