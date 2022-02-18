package uz.qmgroup.pharmabook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uz.qmgroup.pharmabook.screens.main.MainScreen
import uz.qmgroup.pharmabook.ui.theme.PharmaBookTheme

class MainActivity : ComponentActivity() {

    companion object{
        const val SCREEN_HOME_ROUTE="home"
        const val SCREEN_EDITOR_ROUTE="editor"
        const val SCREEN_ABOUT_ROUTE="about"
        const val SCREEN_TAGS_ROUTE="tags"
        const val SCREEN_DETAILS_ROUTE="details"

        const val EDITOR_HOME_ROUTE="editor_home"
        const val EDITOR_MEDICINE_ROUTE="editor_medicine_screen"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PharmaBookTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}
