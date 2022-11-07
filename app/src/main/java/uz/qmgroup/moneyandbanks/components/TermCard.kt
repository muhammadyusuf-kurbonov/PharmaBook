package uz.qmgroup.moneyandbanks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import uz.qmgroup.moneyandbanks.term.Term
import java.util.*

@Composable
fun TermCard(
    modifier: Modifier = Modifier,
    termModels: Term,
    editorEnabled: Boolean = false,
    onEdit: (Term) -> Unit = {},
    onDelete: (Term) -> Unit = {}
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = termModels.name.trim().uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                    Text(
                        text = termModels.definition,
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (editorEnabled) {
                IconButton(onClick = { onEdit(termModels) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
                IconButton(onClick = {
                    onDelete(termModels)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}