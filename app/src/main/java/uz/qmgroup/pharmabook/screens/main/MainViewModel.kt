package uz.qmgroup.pharmabook.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import uz.qmgroup.pharmabook.MainActivity

class MainViewModel: ViewModel() {
    private var navController: NavHostController? = null
    var currentRoute by mutableStateOf(MainActivity.SCREEN_HOME_ROUTE)
    private set

    fun initNavController(navHostController: NavHostController){
        this.navController = navHostController
    }

    fun navigateTo(route: String){
        currentRoute = route
        navController?.navigate(route){
            popUpTo("home")
        }
    }
}