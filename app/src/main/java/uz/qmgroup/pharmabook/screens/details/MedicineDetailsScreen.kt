package uz.qmgroup.pharmabook.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.LabelledText

@Destination
@Composable
fun MedicineDetailsScreen(
    modifier: Modifier = Modifier,
    medicineId: Long,
    medicineDetailsViewModel: MedicineDetailsViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LaunchedEffect(key1 = Unit) {
            medicineDetailsViewModel.loadData(medicineId)
        }

        if (medicineDetailsViewModel.isLoading)
            LinearProgressIndicator()

        Text(text = stringResource(R.string.details), style = MaterialTheme.typography.overline)

        val medicine by medicineDetailsViewModel.medicine.collectAsState()

        if (medicine != null) {
            LabelledText(label = stringResource(R.string.id_number), text = medicine?.id.toString())

            LabelledText(
                label = stringResource(id = R.string.Name),
                text = medicine?.name.orEmpty()
            )

            LabelledText(label = stringResource(id = R.string.Producer), text = medicine?.vendor.orEmpty())

            LabelledText(
                label = stringResource(R.string.position),
                text = "${medicine?.positionRow} / ${medicine?.positionColumn}"
            )
        } else {
            Text(text = stringResource(R.string.medicine_not_found))
        }
    }
}