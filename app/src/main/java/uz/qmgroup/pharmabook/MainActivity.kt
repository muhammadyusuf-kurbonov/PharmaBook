package uz.qmgroup.pharmabook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uz.qmgroup.pharmabook.screens.MainScreen
import uz.qmgroup.pharmabook.ui.theme.PharmaBookTheme

class MainActivity : ComponentActivity() {
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
