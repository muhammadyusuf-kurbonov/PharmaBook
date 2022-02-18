package uz.qmgroup.pharmabook.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import uz.qmgroup.pharmabook.MainActivity.Companion.SCREEN_ABOUT_ROUTE
import uz.qmgroup.pharmabook.MainActivity.Companion.SCREEN_DETAILS_ROUTE
import uz.qmgroup.pharmabook.MainActivity.Companion.SCREEN_EDITOR_ROUTE
import uz.qmgroup.pharmabook.MainActivity.Companion.SCREEN_HOME_ROUTE
import uz.qmgroup.pharmabook.MainActivity.Companion.SCREEN_TAGS_ROUTE
import uz.qmgroup.pharmabook.screens.AboutScreen
import uz.qmgroup.pharmabook.screens.TagsScreen
import uz.qmgroup.pharmabook.screens.details.DetailsScreen
import uz.qmgroup.pharmabook.screens.editor.EditorScreen
import uz.qmgroup.pharmabook.screens.list.ListScreen

@Composable
fun mainNavGraph(navHostController: NavHostController): NavGraph{
    return navHostController.createGraph(SCREEN_HOME_ROUTE){
        composable(SCREEN_HOME_ROUTE){
            ListScreen(
                modifier = Modifier
                    .fillMaxSize(),
                openDetails = {
                    val route1 = "$SCREEN_DETAILS_ROUTE/$it"
                    navHostController.navigate(route1)
                }
            )
        }

        composable(SCREEN_EDITOR_ROUTE){
            EditorScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(SCREEN_ABOUT_ROUTE){
            AboutScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(SCREEN_TAGS_ROUTE){
            TagsScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable("$SCREEN_DETAILS_ROUTE/{medicineId}"){
            val medicineId = it.arguments?.getString("medicineId")?.toLong() ?: -1
            if (medicineId == -1L)
                throw IllegalArgumentException("medicineId not exists")
            DetailsScreen(
                modifier = Modifier.fillMaxSize(),
                medicineId = medicineId,
            )
        }
    }
}