package uz.qmgroup.pharmabook.screens.editor.medicine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.AutoCompleteMultiSelect
import uz.qmgroup.pharmabook.components.OptionsList
import uz.qmgroup.pharmabook.components.ScreenTitle
import uz.qmgroup.pharmabook.components.Section

@Destination
@Composable
fun EditorMedicineScreen(
    modifier: Modifier = Modifier,
    medicineId: Long,
    navigator: DestinationsNavigator,
    editorMedicineViewModel: EditorMedicineViewModel = viewModel(),
) {
    LaunchedEffect(key1 = medicineId) {
        if (medicineId > -1)
            editorMedicineViewModel.loadMedicine(medicineId)
        editorMedicineViewModel.loadMedicines()
    }

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        ScreenTitle(
            title = if (editorMedicineViewModel.medicine == null) stringResource(R.string.New_medicine)
            else stringResource(R.string.Update_medicine)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = editorMedicineViewModel.medicineName,
            onValueChange = editorMedicineViewModel::updateMedicineName,
            label = { Text(stringResource(R.string.Name)) },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = editorMedicineViewModel.medicineVendor,
            onValueChange = editorMedicineViewModel::updateVendor,
            label = { Text(stringResource(R.string.Producer)) },
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = editorMedicineViewModel.medicinePosition.second.toString(),
                onValueChange = editorMedicineViewModel::updateMedicinePositionRow,
                label = { Text(stringResource(R.string.Row)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Spacer(modifier = Modifier.width(4.dp))

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = editorMedicineViewModel.medicinePosition.first.toString(),
                onValueChange = editorMedicineViewModel::updateMedicinePositionColumn,
                label = { Text(stringResource(R.string.Column)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }

        Section(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.diagnoses)
        ) {
            OptionsList(
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.new_diagnose),
                items = editorMedicineViewModel.medicineDiagnoses,
                addItem = { editorMedicineViewModel.medicineDiagnoses.add(it.trim()) },
                deleteItem = { editorMedicineViewModel.medicineDiagnoses.remove(it) }
            )
        }

        Section(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.Tags)
        ) {
            OptionsList(
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.new_tag),
                items = editorMedicineViewModel.medicineTags,
                addItem = editorMedicineViewModel::addTag,
                deleteItem = editorMedicineViewModel::removeTag
            )
        }

        Section(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.alternatives)
        ) {
            AutoCompleteMultiSelect(
                modifier = Modifier.fillMaxWidth(),
                allItems = editorMedicineViewModel.allMedicines.map { it.name },
                selected = editorMedicineViewModel.alternativeMedicines.map { it.name },
                select = editorMedicineViewModel::addAlternative,
                deselect = editorMedicineViewModel::removeAlternative
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
                    editorMedicineViewModel.save()
                    navigator.popBackStack()
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                enabled = editorMedicineViewModel.isSaveButtonEnabled()
            ) {
                Text(stringResource(if (medicineId > -1) R.string.Save else R.string.add))
            }
        }
    }
}
