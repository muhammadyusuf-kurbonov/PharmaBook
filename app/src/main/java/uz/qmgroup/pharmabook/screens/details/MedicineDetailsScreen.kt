package uz.qmgroup.pharmabook.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.LabelledTextCard
import uz.qmgroup.pharmabook.components.MedicineCard

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
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        LaunchedEffect(key1 = Unit) {
            medicineDetailsViewModel.loadData(medicineId)
        }

        if (medicineDetailsViewModel.isLoading)
            LinearProgressIndicator()

        Text(text = stringResource(R.string.details), style = MaterialTheme.typography.titleMedium)

        val medicine by medicineDetailsViewModel.medicine.collectAsState()

        if (medicine != null) {
            LabelledTextCard(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.id_number),
                text = medicine?.id.toString()
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                LabelledTextCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    label = stringResource(id = R.string.Name),
                    text = medicine?.name.orEmpty(),
                    textAlign = TextAlign.Start
                )

                LabelledTextCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    label = stringResource(id = R.string.Producer),
                    text = medicine?.vendor.orEmpty()
                )
            }

            LabelledTextCard(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.position),
                text = "${medicine?.positionRow} / ${medicine?.positionColumn}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.diagnoses),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            medicine?.diagnoses?.forEach {
                Text(
                    modifier = Modifier.padding(8.dp, 0.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (!medicine?.alternatives.isNullOrEmpty()){
                Text(
                    text = stringResource(R.string.alternatives),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )

                medicine?.alternatives?.forEach {
                    MedicineCard(medicine = it)
                }
            }

        } else {
            Text(text = stringResource(R.string.medicine_not_found))
        }
    }
}