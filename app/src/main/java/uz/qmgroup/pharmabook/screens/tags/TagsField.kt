package uz.qmgroup.pharmabook.screens.tags

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.flowlayout.FlowRow
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.Chip
import uz.qmgroup.pharmabook.screens.tags.select_dialog.TagSelectDialog
import uz.qmgroup.pharmabook.tags.Tag

@Composable
fun TagsField(
    modifier: Modifier = Modifier,
    tags: List<Tag>,
    addTag: (Tag) -> Unit,
    removeTag: (Tag) -> Unit,
) {
    var dialogShow by remember {
        mutableStateOf(false)
    }

    if (dialogShow) {
        Dialog(
            onDismissRequest = { dialogShow = false },
        ) {
            TagSelectDialog(
                onTagSelected = addTag,
                modifier = Modifier.wrapContentHeight(),
                excludedTags = tags
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        if (tags.isEmpty())
            Text(
                text = stringResource(R.string.No_tags),
                modifier = modifier
                    .weight(1f)
            )
        else
            FlowRow(
                modifier = modifier.weight(1f),
            ) {
                tags.forEach { tag ->
                    Chip(
                        modifier = Modifier.padding(4.dp, 0.dp),
                        text = tag.label,
                        onClick = { removeTag(tag) }
                    )
                }
            }

        IconButton(onClick = { dialogShow = true }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
        }
    }
}
