package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LabelledText(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    spaceBetween: Boolean = true
){
    Row(modifier = modifier) {
        Text(text = label, style = textStyle.copy(fontWeight = FontWeight.Bold))
        if (spaceBetween) {
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(text = text, style = textStyle)
    }
}