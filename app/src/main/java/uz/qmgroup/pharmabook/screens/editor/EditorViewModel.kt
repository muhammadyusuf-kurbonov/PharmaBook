package uz.qmgroup.pharmabook.screens.editor

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import uz.qmgroup.pharmabook.MainActivity

class EditorViewModel: ViewModel() {
    private var navHostController: NavHostController? = null

    fun initNavController(navHostController: NavHostController){
        this.navHostController = navHostController
    }

    fun navigateTo(route: String){
        navHostController?.navigate(route){
            popUpTo(MainActivity.EDITOR_HOME_ROUTE)
        }
    }
}