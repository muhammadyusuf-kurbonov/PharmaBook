package uz.qmgroup.pharmabook.screens.tags

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import uz.qmgroup.pharmabook.components.TagsList
import uz.qmgroup.pharmabook.repos.TagsRepo
import uz.qmgroup.pharmabook.screens.destinations.MedicineByTagScreenDestination
import uz.qmgroup.pharmabook.tags.Tag

@Destination
@Composable
fun TagsScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Tag>(), producer = {
        TagsRepo().getTags().collect {
            this.value = it
            loading = false
        }
    })

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
                navigator.navigate(MedicineByTagScreenDestination(it.label))
            }
        )
    }
}
