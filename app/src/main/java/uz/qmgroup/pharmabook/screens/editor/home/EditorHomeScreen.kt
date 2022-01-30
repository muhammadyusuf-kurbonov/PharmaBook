package uz.qmgroup.pharmabook.screens.editor.home

import androidx.compose.foundation.layout.Box
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
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.medicines.Medicine

@Composable
fun EditorHomeScreen(
    modifier: Modifier = Modifier,
    editorHomeViewModel: EditorHomeViewModel = viewModel(),
    onEdit: (Medicine) -> Unit = {},
    onAdd: () -> Unit = {},
){

    LaunchedEffect(key1 = Unit){
        editorHomeViewModel.reloadData()
    }

    Box(modifier = modifier) {
        MedicinesList(
            modifier = modifier,
            list = editorHomeViewModel.list,
            loading = editorHomeViewModel.loading,
            editorEnabled = true,
            onDelete = editorHomeViewModel::deleteMedicine,
            onEdit = onEdit,
        )

        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onAdd
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}