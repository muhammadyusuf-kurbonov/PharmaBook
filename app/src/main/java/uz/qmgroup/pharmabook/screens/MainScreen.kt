package uz.qmgroup.pharmabook.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import uz.qmgroup.pharmabook.R

enum class Screens {
    Main,
    Profile,
    Tags,
    RepoEditor,
    About
}

@Composable
fun MainScreen() {

    var currentScreen by remember {
        mutableStateOf(Screens.Main)
    }

    BackHandler(
        enabled = currentScreen != Screens.Main
    ) {
            currentScreen = Screens.Main
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                    MainScreenMenu(
                        navigate = {
                            currentScreen = it
                        }
                    )
                }
            )
        },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                // A surface container using the 'background' color from the theme
                when(currentScreen) {
                    Screens.Main -> ListScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    )
                    Screens.RepoEditor -> EditorScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    )
                    Screens.About -> AboutScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    )
                    Screens.Tags -> TagsScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = Icons.Default.List, contentDescription = null)
                    },
                    label = { Text(stringResource(R.string.Home)) },
                    onClick = { currentScreen = Screens.Main },
                    selected = currentScreen == Screens.Main
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tag_black_24dp),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(R.string.Tags)) },
                    onClick = { currentScreen = Screens.Tags },
                    selected = currentScreen == Screens.Tags
                )

                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    },
                    label = { Text(stringResource(R.string.Profile)) },
                    onClick = { currentScreen = Screens.Profile },
                    selected = currentScreen == Screens.Profile
                )
            }
        }
    )
}

@Composable
fun MainScreenMenu(
    navigate: (Screens) -> Unit = {}
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
                navigate(Screens.RepoEditor)
            }
        ) {
            Text(stringResource(R.string.Editor))
        }
        DropdownMenuItem(
            onClick = {
                navigate(Screens.About)
            }
        ) {
            Text(stringResource(R.string.About))
        }
    }

}