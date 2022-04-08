package uz.qmgroup.pharmabook.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.SettingsItem
import uz.qmgroup.pharmabook.screens.destinations.AboutScreenDestination
import uz.qmgroup.pharmabook.screens.destinations.EditorHomeScreenDestination

@Destination
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SettingsItem(label = stringResource(id = R.string.Medicines)) {
            navigator.navigate(EditorHomeScreenDestination)
        }

        SettingsItem(label = stringResource(id = R.string.About)) {
            navigator.navigate(AboutScreenDestination)
        }
    }
}