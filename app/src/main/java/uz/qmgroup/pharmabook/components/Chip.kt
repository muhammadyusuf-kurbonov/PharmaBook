package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick(text) },
        shape = RoundedCornerShape(99.dp),
    ) {
        Text(text = text, style = MaterialTheme.typography.body2)
    }
}