package uz.qmgroup.pharmabook.screens.tags.select_dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.TagsList
import uz.qmgroup.pharmabook.tags.Tag

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagSelectDialog(
    modifier: Modifier = Modifier,
    tagSelectViewModel: TagSelectViewModel = viewModel(),
    excludedTags: List<Tag> = emptyList(),
    onTagSelected: (Tag) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        tagSelectViewModel.loadData()
    }

    tagSelectViewModel.excludedTags = excludedTags

    Card(modifier = modifier.fillMaxWidth()) {
        val tags by tagSelectViewModel.tags.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = stringResource(R.string.select_tag), style = MaterialTheme.typography.subtitle1)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tagSelectViewModel.query,
                onValueChange = tagSelectViewModel::updateQuery,
                placeholder = { Text(text = stringResource(id = R.string.search)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") }
            )
            TagsList(
                list = tags, onClick = onTagSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.6f),
                footer = {
                    if (tagSelectViewModel.query.isNotEmpty()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            elevation = 8.dp,
                            onClick = {
                                tagSelectViewModel.createTag(tagSelectViewModel.query)
                            }
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.create_tag,
                                    tagSelectViewModel.query
                                ),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                            )
                        }
                    }
                }
            )
        }
    }
}