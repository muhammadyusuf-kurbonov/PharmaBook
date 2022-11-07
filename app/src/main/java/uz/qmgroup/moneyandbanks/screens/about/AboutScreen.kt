package uz.qmgroup.moneyandbanks.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import uz.qmgroup.moneyandbanks.BuildConfig
import uz.qmgroup.moneyandbanks.R

@Destination
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = stringResource(id = R.string.About), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Text(text = "This is a demo version of Glossary for Money and Banks", style = MaterialTheme.typography.body1)
        // version
        Text(text = "Version: " + BuildConfig.VERSION_NAME, style = MaterialTheme.typography.body1)
        // made by
        Text(text = "Made by: QM Group", style = MaterialTheme.typography.body1)
    }
}