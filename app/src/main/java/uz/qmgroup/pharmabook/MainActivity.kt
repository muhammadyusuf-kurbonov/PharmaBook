package uz.qmgroup.pharmabook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import uz.qmgroup.pharmabook.screens.main.MainScreen
import uz.qmgroup.pharmabook.ui.theme.PharmaBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets {
                PharmaBookTheme {
                    // A surface container using the 'background' color from the theme
                    MainScreen(
                        modifier = Modifier
                            .statusBarsPadding()
                            .navigationBarsWithImePadding()
                    )
                }
            }
        }
    }
}
