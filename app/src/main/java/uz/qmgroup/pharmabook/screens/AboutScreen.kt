package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.BuildConfig

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp, 8.dp)
    ) {
        Text(text = "About", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "This is a demo version of PharmaBook", style = MaterialTheme.typography.body1)
        // version
        Text(text = "Version: " + BuildConfig.VERSION_NAME, style = MaterialTheme.typography.body1)
        // made by
        Text(text = "Made by: QM Group", style = MaterialTheme.typography.body1)
    }
}