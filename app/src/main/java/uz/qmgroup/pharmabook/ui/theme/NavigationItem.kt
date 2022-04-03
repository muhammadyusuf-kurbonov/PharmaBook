package uz.qmgroup.pharmabook.ui.theme

import androidx.compose.ui.graphics.vector.ImageVector
import uz.qmgroup.pharmabook.screens.destinations.DirectionDestination

data class NavigationItem(
    val icon: ImageVector,
    val label: String,
    val route: DirectionDestination
)