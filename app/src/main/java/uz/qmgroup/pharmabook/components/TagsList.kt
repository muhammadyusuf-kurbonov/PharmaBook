package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Tag
import uz.qmgroup.pharmabook.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagsList(
    modifier: Modifier = Modifier,
    list: List<String>,
    onClick: (String) -> Unit,
    footer: @Composable () -> Unit = {},
) {
    if (list.isNotEmpty()) {
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(list) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 8.dp,
                    onClick = {
                        onClick(it)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Tag,
                            contentDescription = null,
                            modifier = Modifier.height(24.dp),
                            tint = MaterialTheme.colors.primary
                        )
                        Text(text = it, style = MaterialTheme.typography.body1)
                    }
                }
            }
            item {
                footer()
            }
        }
    } else {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.No_tags)
            )
            footer()
        }
    }
}