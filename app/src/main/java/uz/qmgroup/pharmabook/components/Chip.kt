package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit
) {
    Surface(
        modifier = modifier,
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.secondary)
    ) {
        Row(modifier = Modifier.clickable { onClick(text) }) {
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}