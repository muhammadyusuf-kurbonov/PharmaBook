package uz.qmgroup.pharmabook.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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
        Row(modifier = Modifier.fillMaxWidth().clickable {
            navigator.navigate(EditorHomeScreenDestination)
        }) {
            Text(
                text = "Medicines",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(4.dp)
            )
        }

        Row(modifier = Modifier.fillMaxWidth().clickable {
            navigator.navigate(AboutScreenDestination)
        }) {
            Text(
                text = "About",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}