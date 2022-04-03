package uz.qmgroup.pharmabook.components

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import uz.qmgroup.pharmabook.components.autocomplete.AutoCompleteBox
import uz.qmgroup.pharmabook.components.autocomplete.asAutoCompleteEntities
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AutoCompleteMultiSelect(
    modifier: Modifier = Modifier,
    allItems: List<String>,
    selected: List<String>,
    select: (String) -> Unit,
    deselect: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            selected.forEach {
                Chip(onClick = { deselect(it) }) {
                    Text(text = it)
                }
            }
        }


        val autoCompleteEntities = allItems
            .filter { !selected.contains(it) }
            .asAutoCompleteEntities { entity, query ->
                entity.contains(
                    query,
                    ignoreCase = true
                )
            }
        Log.d("filter", "All items are ${autoCompleteEntities.size}")
        AutoCompleteBox(
            items = autoCompleteEntities,
            itemContent = {
                Text(
                    text = it.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        ) {
            this.boxWidthPercentage = 1f
            this.boxBorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer)

            this.onItemSelected {
                select(it.value)
                query = ""
                this.isSearching = true
            }

            androidx.compose.material3.OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        this.isSearching = it.hasFocus or it.isFocused
                    },
                value = query,
                onValueChange = { newQuery ->
                    query =
                        newQuery.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    this.filter(newQuery)
                },
            )
        }
    }
}