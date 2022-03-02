package uz.qmgroup.pharmabook.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle
import uz.qmgroup.pharmabook.components.LabelledText

@Destination(style = DestinationStyle.Dialog::class)
@Composable
fun MedicineDetailsScreen(
    modifier: Modifier = Modifier,
    medicineId: Long,
    medicineDetailsViewModel: MedicineDetailsViewModel = viewModel()
){
    LaunchedEffect(key1 = Unit) {
        medicineDetailsViewModel.loadData(medicineId)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        if (medicineDetailsViewModel.isLoading)
            LinearProgressIndicator()

        Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Details", style = MaterialTheme.typography.overline)

            val medicine = medicineDetailsViewModel.medicine
            if (medicine != null) {
                LabelledText(label = "ID number", text = medicine.id.toString())

                LabelledText(
                    label = "Name",
                    text = medicine.name
                )

                LabelledText(label = "Vendor", text = medicine.vendor)

                LabelledText(label = "Position", text = "${medicine.positionRow} / ${medicine.positionColumn}")
            }
        }
    }
}