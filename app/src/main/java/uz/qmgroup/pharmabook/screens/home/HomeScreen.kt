package uz.qmgroup.pharmabook.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Search
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.screens.destinations.MedicineDetailsScreenDestination
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
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

    Column(modifier = modifier.padding(8.dp, 0.dp)) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, bottom = 0.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(2.dp),
                    imageVector = FontAwesomeIcons.Solid.Search,
                    contentDescription = ""
                )
                BasicTextField(
                    value = searchPattern,
                    onValueChange = { newValue ->
                        searchPattern =
                            newValue.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            this.defaultKeyboardAction(ImeAction.Search)
                            viewModel.startSearch(searchPattern)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleLarge
                )
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