package uz.qmgroup.pharmabook.screens.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigateTo
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Tags
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.screens.NavGraphs
import uz.qmgroup.pharmabook.screens.destinations.*
import uz.qmgroup.pharmabook.screens.navDestination

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    MainScreenMenu(
                        navigate = {
                            navHostController.navigateTo(it)
                        }
                    )
                }
            )
        },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                DestinationsNavHost(
                    modifier = Modifier.padding(it),
                    navGraph = NavGraphs.root,
                    navController = navHostController
                )
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Home,
                            contentDescription = null,
                            modifier = Modifier.height(24.dp)
                        )
                    },
                    label = { Text(stringResource(R.string.Home)) },
                    onClick = { navHostController.navigateTo(HomeScreenDestination) },
                    selected = currentBackStackEntry?.navDestination == HomeScreenDestination
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Tags,
                            contentDescription = null,
                            modifier = Modifier.height(24.dp)
                        )
                    },
                    label = { Text(stringResource(R.string.Tags)) },
                    onClick = { navHostController.navigateTo(TagsScreenDestination) },
                    selected = currentBackStackEntry?.navDestination == TagsScreenDestination
                )

//                BottomNavigationItem(
//                    icon = {
//                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
//                    },
//                    label = { Text(stringResource(R.string.Profile)) },
//                    onClick = { currentScreen = Screens.Profile },
//                    selected = currentScreen == Screens.Profile
//                )
            }
        }
    )
}

@Composable
fun MainScreenMenu(
    navigate: (DirectionDestination) -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }

    IconButton(onClick = {
        menuExpanded = !menuExpanded
    }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }

    DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
        DropdownMenuItem(
            onClick = {
                navigate(EditorHomeScreenDestination)
                menuExpanded = false
            }
        ) {
            Text(stringResource(R.string.Editor))
        }

        DropdownMenuItem(
            onClick = {
                navigate(AboutScreenDestination)
                menuExpanded = false
            }
        ) {
            Text(stringResource(R.string.About))
        }
    }

}