package uz.qmgroup.pharmabook.screens.editor.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.screens.destinations.EditorMedicineScreenDestination

@Destination
@Composable
fun EditorHomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    editorHomeViewModel: EditorHomeViewModel = viewModel(),
){
    LaunchedEffect(key1 = Unit){
        editorHomeViewModel.startSearch()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = editorHomeViewModel.searchPattern,
                onValueChange = editorHomeViewModel::updateSearchPattern,
                placeholder = { Text(text = stringResource(R.string.search), style = MaterialTheme.typography.bodyMedium) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        this.defaultKeyboardAction(ImeAction.Search)
                        editorHomeViewModel.startSearch()
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
                shape = RoundedCornerShape(8.dp),
                textStyle = MaterialTheme.typography.bodyMedium
            )

            MedicinesList(
                modifier = modifier,
                list = editorHomeViewModel.list,
                loading = editorHomeViewModel.loading,
                editorEnabled = true,
                onDelete = editorHomeViewModel::deleteMedicine,
                onEdit = { navigator.navigate(EditorMedicineScreenDestination(it.id)) },
                onCopy = editorHomeViewModel::duplicateMedicine
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { navigator.navigate(EditorMedicineScreenDestination(-1)) }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}