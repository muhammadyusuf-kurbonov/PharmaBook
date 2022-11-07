package uz.qmgroup.moneyandbanks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uz.qmgroup.moneyandbanks.R
import uz.qmgroup.moneyandbanks.term.Term

@Composable
fun TermsList(
    modifier: Modifier = Modifier,
    list: List<Term>,
    loading: Boolean = false,
    editorEnabled: Boolean = false,
    onEdit: (Term) -> Unit = {},
    onDelete: (Term) -> Unit = {},
    onCLick: (Term) -> Unit = {},
) {
    Column {
        if (loading) {
            LinearProgressIndicator(
                modifier.fillMaxWidth()
            )
        } else {
            if (list.isNotEmpty()) {
                LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    items(list) {
                        TermCard(
                            modifier = Modifier.clickable { onCLick(it) },
                            termModels = it,
                            editorEnabled = editorEnabled,
                            onEdit = onEdit,
                            onDelete = onDelete
                        )
                    }
                }
            } else Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.No_terms),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}
