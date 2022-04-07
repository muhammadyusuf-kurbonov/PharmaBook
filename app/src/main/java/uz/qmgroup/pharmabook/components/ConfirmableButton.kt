package uz.qmgroup.pharmabook.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

interface ConfirmableButtonScope {
    fun requestConfirmation()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ConfirmableButton(
    modifier: Modifier = Modifier,
    confirm: () -> Unit,
    content: @Composable ConfirmableButtonScope.() -> Unit
) {
    var isConfirmMode by remember { mutableStateOf(false) }

    val scope = remember {
        object: ConfirmableButtonScope {
            override fun requestConfirmation() {
                isConfirmMode = true
            }
        }
    }

    AnimatedContent(modifier = modifier, targetState = isConfirmMode){
        if (it)
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                OutlinedButton(onClick = { isConfirmMode = false }) {
                    Text(text = "No")
                }
                OutlinedButton(onClick = { confirm(); isConfirmMode = false }) {
                    Text(text = "Yes")
                }
            }
        else
            scope.content()
    }
}

@Preview
@Composable
fun PreviewConfirmableButton() {
    ConfirmableButton(confirm = {}) {
        IconButton(onClick = { requestConfirmation() }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "", tint = Color.Red)
        }
    }
}