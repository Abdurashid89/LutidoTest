package uz.abdurashid.testtaskmobile.screens.bottom_nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uz.abdurashid.testtaskmobile.ui.theme.Color_Black
import uz.abdurashid.testtaskmobile.ui.theme.Color_Bottom_Nav
import uz.abdurashid.testtaskmobile.ui.theme.Color_Gray
import uz.abdurashid.testtaskmobile.util.Screen

@Composable
fun BroneBottomNavigation(navController: NavController) {
    val items = listOf(
        Screen.Favourite,
        Screen.Map,
        Screen.Contacts
    )

    BottomNavigation(
        modifier = Modifier.height(86.dp),
        backgroundColor = Color_Bottom_Nav,
        contentColor = Color_Bottom_Nav
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            val selectedColor = if (currentRoute == screen.route) Color_Black else Color_Gray
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        colorFilter = ColorFilter.tint(selectedColor)
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            // Navigatsiya stekni boshqarish
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = true,
                selectedContentColor = Color_Black,
                unselectedContentColor = Color_Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    BroneBottomNavigation(navController = navController)
}
