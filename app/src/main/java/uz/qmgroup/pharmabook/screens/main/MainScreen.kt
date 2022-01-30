package uz.qmgroup.pharmabook.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.qmgroup.pharmabook.MainActivity
import uz.qmgroup.pharmabook.R

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {

    val navHostController = rememberNavController()
    mainViewModel.initNavController(navHostController)

    BackHandler(
        enabled = mainViewModel.currentRoute != MainActivity.SCREEN_HOME_ROUTE
    ) {
        mainViewModel.navigateTo(MainActivity.SCREEN_HOME_ROUTE)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    MainScreenMenu(
                        navigate = {
                            mainViewModel.navigateTo(it)
                        }
                    )
                }
            )
        },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                NavHost(
                    navController = navHostController,
                    graph = mainNavGraph(navHostController = navHostController),
                    modifier = Modifier.padding(it)
                )
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = Icons.Default.List, contentDescription = null)
                    },
                    label = { Text(stringResource(R.string.Home)) },
                    onClick = { mainViewModel.navigateTo(MainActivity.SCREEN_HOME_ROUTE) },
                    selected = mainViewModel.currentRoute == MainActivity.SCREEN_HOME_ROUTE
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tag_black_24dp),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(R.string.Tags)) },
                    onClick = { mainViewModel.navigateTo(MainActivity.SCREEN_TAGS_ROUTE) },
                    selected = mainViewModel.currentRoute == MainActivity.SCREEN_TAGS_ROUTE
                )
//
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
    navigate: (String) -> Unit = {}
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
                navigate(MainActivity.SCREEN_EDITOR_ROUTE)
                menuExpanded = false
            }
        ) {
            Text(stringResource(R.string.Editor))
        }
        DropdownMenuItem(
            onClick = {
                navigate(MainActivity.SCREEN_ABOUT_ROUTE)
                menuExpanded = false
            }
        ) {
            Text(stringResource(R.string.About))
        }
    }

}