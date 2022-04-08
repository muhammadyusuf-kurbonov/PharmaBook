package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
        .padding(8.dp, 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
        )
    }
    Divider()
}