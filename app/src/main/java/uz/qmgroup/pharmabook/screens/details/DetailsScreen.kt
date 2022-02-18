package uz.qmgroup.pharmabook.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.qmgroup.pharmabook.components.LabelledText

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    medicineId: Long,
    detailsViewModel: DetailsViewModel = viewModel()
){
    LaunchedEffect(key1 = Unit) {
        detailsViewModel.loadData(medicineId)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        if (detailsViewModel.isLoading)
            LinearProgressIndicator()

        Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Medicine #$medicineId", style = MaterialTheme.typography.caption)

            val medicine = detailsViewModel.medicine
            if (medicine != null) {
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