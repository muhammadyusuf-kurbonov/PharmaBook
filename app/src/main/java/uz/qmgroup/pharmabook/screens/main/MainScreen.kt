package uz.qmgroup.pharmabook.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigateTo
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.List
import compose.icons.fontawesomeicons.solid.Tags
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.screens.NavGraphs
import uz.qmgroup.pharmabook.screens.destinations.HomeScreenDestination
import uz.qmgroup.pharmabook.screens.destinations.SettingsScreenDestination
import uz.qmgroup.pharmabook.screens.destinations.TagsScreenDestination
import uz.qmgroup.pharmabook.ui.theme.NavigationItem

val menuItems = listOf(
    NavigationItem(
        icon = FontAwesomeIcons.Solid.List,
        label = "Medicines",
        route = HomeScreenDestination
    ),
    NavigationItem(
        icon = FontAwesomeIcons.Solid.Tags,
        label = "Tags",
        route = TagsScreenDestination
    ),
    NavigationItem(
        icon = FontAwesomeIcons.Solid.Cog,
        label = "Settings",
        route = SettingsScreenDestination
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()

    ModalNavigationDrawer(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        drawerContent = {}
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            },
            content = {
                DestinationsNavHost(
                    modifier = Modifier.padding(it),
                    navGraph = NavGraphs.root,
                    navController = navHostController
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    menuItems.forEach {
                        NavigationBarItem(
                            selected = currentBackStackEntry?.destination?.route == it.route.route,
                            onClick = { navHostController.navigateTo(it.route) },
                            label = { Text(text = it.label) },
                            icon = { Icon(
                                modifier = Modifier.width(24.dp).height(24.dp),
                                imageVector = it.icon,
                                contentDescription = it.label
                            ) }
                        )
                    }
                }
            }
        )
    }
}