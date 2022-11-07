package uz.qmgroup.moneyandbanks.screens.editor.medicine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.moneyandbanks.R
import uz.qmgroup.moneyandbanks.components.AutoCompleteMultiSelect
import uz.qmgroup.moneyandbanks.components.OptionsList
import uz.qmgroup.moneyandbanks.components.Section

@Destination
@Composable
fun EditorMedicineScreen(
    modifier: Modifier = Modifier,
    medicineId: Long,
    navigator: DestinationsNavigator,
    editorTermViewModel: EditorTermViewModel = viewModel(),
) {
    LaunchedEffect(key1 = medicineId) {
        if (medicineId > -1)
            editorTermViewModel.loadMedicine(medicineId)
        editorTermViewModel.loadMedicines()
    }

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = editorTermViewModel.termName,
            onValueChange = editorTermViewModel::updateMedicineName,
            label = { Text(stringResource(R.string.Name)) },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = editorTermViewModel.termDefinition,
            onValueChange = editorTermViewModel::updateVendor,
            label = { Text(stringResource(R.string.Definition)) },
        )

        Section(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.Tags)
        ) {
            OptionsList(
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.new_tag),
                items = editorTermViewModel.tags,
                addItem = editorTermViewModel::addTag,
                deleteItem = editorTermViewModel::removeTag
            )
        }

        Section(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.synonyms)
        ) {
            AutoCompleteMultiSelect(
                modifier = Modifier.fillMaxWidth(),
                allItems = editorTermViewModel.allTerms.map { it.name },
                selected = editorTermViewModel.synonymTerms.map { it.name },
                select = editorTermViewModel::addAlternative,
                deselect = editorTermViewModel::removeAlternative
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navigator.popBackStack() },
                modifier = Modifier
                    .padding(8.dp, 0.dp),
            ) {
                Text(stringResource(android.R.string.cancel))
            }

            Button(
                onClick = {
                    editorTermViewModel.save()
                    navigator.popBackStack()
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                enabled = editorTermViewModel.isSaveButtonEnabled()
            ) {
                Text(stringResource(if (medicineId > -1) R.string.Save else R.string.add))
            }
        }
    }
}
