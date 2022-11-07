package uz.qmgroup.moneyandbanks.screens.editor.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.moneyandbanks.components.TermsList
import uz.qmgroup.moneyandbanks.screens.destinations.EditorMedicineScreenDestination

@Destination
@Composable
fun EditorHomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    editorHomeViewModel: EditorHomeViewModel = viewModel(),
){
    LaunchedEffect(key1 = Unit){
        editorHomeViewModel.reloadData()
    }

    Box(modifier = modifier.fillMaxSize()) {
        TermsList(
            modifier = modifier,
            list = editorHomeViewModel.list,
            loading = editorHomeViewModel.loading,
            editorEnabled = true,
            onDelete = editorHomeViewModel::deleteMedicine,
            onEdit = { navigator.navigate(EditorMedicineScreenDestination(it.id)) },
        )

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