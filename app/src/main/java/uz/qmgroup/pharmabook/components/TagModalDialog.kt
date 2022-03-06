package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.repos.TagsRepo
import uz.qmgroup.pharmabook.tags.Tag
import java.util.*


@Composable
fun TagModalDialog(
    hideDialog: () -> Unit,
    scope: CoroutineScope,
) {
    Dialog(onDismissRequest = hideDialog) {
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
                            hideDialog()
                        }
                    }) {
                    Text(stringResource(R.string.add))
                }
            }
        }
    }
}