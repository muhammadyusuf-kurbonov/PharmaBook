package uz.qmgroup.pharmabook.screens.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import uz.qmgroup.pharmabook.screens.destinations.MedicineDetailsScreenDestination

@Destination
@Composable
fun MedicineByTagScreen(
    modifier: Modifier = Modifier,
    tag: String,
    navigator: DestinationsNavigator
) {
    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Medicine>(), producer = {
        this.value = MedicinesRepo().getMedicinesByTags(
            listOf(tag)
        )
        loading = false
    }, key1 = tag)


    if (loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.medicines_by_tag, tag),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            MedicinesList(
                modifier = Modifier.fillMaxWidth(),
                list = list,
                onCLick = {
                    navigator.navigate(MedicineDetailsScreenDestination(it.id))
                }
            )
        }
    }
}