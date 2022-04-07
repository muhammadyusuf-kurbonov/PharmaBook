package uz.qmgroup.pharmabook.screens.tags

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.components.TagsList
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import uz.qmgroup.pharmabook.screens.destinations.MedicineByTagScreenDestination

@Destination
@Composable
fun TagsScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    var loading by remember {
        mutableStateOf(true)
    }

    val list = remember { mutableStateListOf<String>() }

    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.IO) {
            try {
                loading = true
                list.addAll(MedicinesRepo().getMedicines().flatMap { it.tags ?: emptyList() }
                    .distinct())
            } finally {
                loading = false
            }
        }
    }

    if (loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        TagsList(
            modifier = modifier
                .padding(16.dp, 8.dp),
            list = list,
            onClick = {
                navigator.navigate(MedicineByTagScreenDestination(it))
            }
        )
    }
}
