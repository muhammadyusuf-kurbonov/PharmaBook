package uz.qmgroup.moneyandbanks.screens.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.moneyandbanks.R
import uz.qmgroup.moneyandbanks.components.TermsList
import uz.qmgroup.moneyandbanks.repos.TermsRepo
import uz.qmgroup.moneyandbanks.screens.destinations.TermDetailsScreenDestination
import uz.qmgroup.moneyandbanks.term.Term

@Destination
@Composable
fun TermsByTagScreen(
    modifier: Modifier = Modifier,
    tag: String,
    navigator: DestinationsNavigator
) {
    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Term>(), producer = {
        this.value = TermsRepo().getTermsByTags(
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
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TermsList(
                modifier = Modifier.fillMaxWidth(),
                list = list,
                onCLick = {
                    navigator.navigate(TermDetailsScreenDestination(it.id))
                }
            )
        }
    }
}