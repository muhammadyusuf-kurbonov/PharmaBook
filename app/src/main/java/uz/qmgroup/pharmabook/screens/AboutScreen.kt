package uz.qmgroup.pharmabook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
        modifier = modifier
    ) {
        Text(text = "About", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "This is a demo app for pharmabook", style = MaterialTheme.typography.body1)
        // version
        Text(text = "Version: " + BuildConfig.VERSION_NAME, style = MaterialTheme.typography.body1)
        // made by
        Text(text = "Made by: QM Group", style = MaterialTheme.typography.body1)
    }
}