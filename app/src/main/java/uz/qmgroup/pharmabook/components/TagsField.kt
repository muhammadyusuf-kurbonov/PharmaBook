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
import uz.qmgroup.pharmabook.repos.TagsRepo
import uz.qmgroup.pharmabook.tags.Tag
import java.util.*

@Composable
fun TagsField(
    tags: List<Tag>,
    addTag: (Tag) -> Unit,
    removeTag: (Tag) -> Unit,
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

    val allTagModels by produceState(initialValue = emptyList<Tag>(), producer = {
        scope.launch {
            value = TagsRepo().getTags()
            loading = false
        }
    }, key1 = updateController)


    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlowRow(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
                .clickable { dropdownShow = true }
                .padding(4.dp),
        ) {
            tags.forEach {tag ->
                Chip(
                    modifier = Modifier.padding(4.dp, 0.dp),
                    text = tag.label,
                    onClick = { removeTag(tag) }
                )
            }
            if (tags.isEmpty()) Text(text = stringResource(R.string.No_tags))
        }
        IconButton(onClick = { addTagDialog = !addTagDialog }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        DropdownMenu(expanded = dropdownShow, onDismissRequest = { dropdownShow = false }) {
            if (!loading) {
                allTagModels.filterNot {
                    tags.contains(it)
                }.forEach {
                    DropdownMenuItem(onClick = { addTag(it) }) {
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
                        onValueChange = { newValue ->
                            tag =
                                newValue.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }
                        },
                        modifier = Modifier.padding(16.dp),
                        label = { Text(stringResource(R.string.Tag_label)) }
                    )
                    TextButton(
                        modifier = Modifier.align(Alignment.End),
                        enabled = tag.isNotEmpty(),
                        onClick = {
                            scope.launch {
                                TagsRepo().createTag(Tag(label = tag.trim(), id = 0))
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