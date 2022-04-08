package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Copy

@Composable
fun EditControls(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
    onCopy: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null
) {
    var deleteConfirm by remember { mutableStateOf(false) }

    Row(modifier = modifier) {
        if (!deleteConfirm) {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            if (onCopy != null)
                IconButton(onClick = onCopy) {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        imageVector = FontAwesomeIcons.Solid.Copy,
                        contentDescription = null
                    )
                }
        }
        if (onDelete != null)
            ConfirmableButton(confirm = onDelete) {
                deleteConfirm = this.confirmMode

                IconButton(onClick = {
                    requestConfirmation()
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