package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.models.Tag
import uz.qmgroup.pharmabook.repos.TagsRepo

@Composable
fun TagsField(
    tags: List<String>,
    addTag: (String) -> Unit,
    removeTag: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var dropdownShow by remember {
        mutableStateOf(false)
    }

    var addTagDialog by remember {
        mutableStateOf(false)
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var updateController by remember {
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()

    val allTags by produceState(initialValue = emptyList<Tag>(), producer = {
            scope.launch {
                value = TagsRepo().getTags()?.toObjects(Tag::class.java) ?: emptyList()
                loading = false
            }
        }, key1 = updateController)


    Row(modifier = modifier
        .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlowRow(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
            .clickable { dropdownShow = true }
            .padding(4.dp),
        ) {
            tags.forEach {
                Chip(
                    modifier = Modifier.padding(4.dp, 0.dp),
                    text = it,
                    onClick = { tag -> removeTag(tag) }
                )
            }
            if (tags.isEmpty()) Text(text = stringResource(R.string.No_tags))
        }
        IconButton(onClick = { addTagDialog = !addTagDialog }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        DropdownMenu(expanded = dropdownShow, onDismissRequest = { dropdownShow = false }) {
            if (!loading) {
                allTags.filterNot {
                    tags.contains(it.label)
                }.forEach {
                    DropdownMenuItem(onClick = { addTag(it.label) }) {
                        Text(text = it.label)
                    }
                }
            } else {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenuItem(onClick = {}) {
                    Text(text = stringResource(R.string.Loading))
                }
            }
        }
    }

    if (addTagDialog) {
        Dialog(onDismissRequest = { addTagDialog = false }) {
            var tag by remember {
                mutableStateOf("")
            }
            Surface(
                color = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.contentColorFor(
                    MaterialTheme.colors.surface
                )
            ) {
                Column {
                    TextField(
                        value = tag,
                        onValueChange = { tag = it },
                        modifier = Modifier.padding(16.dp),
                        label = { Text(stringResource(R.string.Tag_label)) }
                    )
                    TextButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            scope.launch {
                                TagsRepo().addTag(Tag(label = tag))
                                updateController++
                                addTagDialog = false
                            }
                        }) {
                        Text(stringResource(R.string.add))
                    }
                }
            }
        }
    }
}