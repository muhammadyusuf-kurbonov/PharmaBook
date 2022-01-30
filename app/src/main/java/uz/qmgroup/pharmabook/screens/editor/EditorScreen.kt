package uz.qmgroup.pharmabook.screens.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.qmgroup.pharmabook.MainActivity
import uz.qmgroup.pharmabook.screens.editor.home.EditorHomeScreen
import uz.qmgroup.pharmabook.screens.editor.medicine.EditorMedicineScreen


@Composable
fun EditorScreen(
    modifier: Modifier = Modifier,
    editorViewModel: EditorViewModel = viewModel()
) {
    val navHostController = rememberNavController()
    editorViewModel.initNavController(navHostController)

    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = MainActivity.EDITOR_HOME_ROUTE
    ) {
        composable(MainActivity.EDITOR_HOME_ROUTE) {
            EditorHomeScreen(
                modifier = Modifier.fillMaxSize(),
                onEdit = {
                    editorViewModel.navigateTo(MainActivity.EDITOR_MEDICINE_ROUTE + "/" + it.id)
                },
                onAdd = {
                    editorViewModel.navigateTo(MainActivity.EDITOR_MEDICINE_ROUTE)
                }
            )
        }
        composable(MainActivity.EDITOR_MEDICINE_ROUTE) {
            EditorMedicineScreen(
                modifier = Modifier.fillMaxSize(),
                cancel = {
                    editorViewModel.navigateTo(MainActivity.EDITOR_HOME_ROUTE)
                }
            )
        }
        composable(
            "${MainActivity.EDITOR_MEDICINE_ROUTE}/{medicineId}",
            listOf(navArgument("medicineId") { type = NavType.LongType })
        ) {
            EditorMedicineScreen(
                modifier = Modifier.fillMaxSize(),
                medicineId = it.arguments?.getLong("medicineId"),
                cancel = {
                    editorViewModel.navigateTo(MainActivity.EDITOR_HOME_ROUTE)
                }
            )
        }
    }
}