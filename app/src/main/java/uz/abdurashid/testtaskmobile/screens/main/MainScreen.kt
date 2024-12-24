package uz.abdurashid.testtaskmobile.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uz.abdurashid.testtaskmobile.screens.bottom_nav.BroneBottomNavigation
import uz.abdurashid.testtaskmobile.util.NavigationGraph

@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            BroneBottomNavigation(navController)
        }
    ) { innerPadding ->
        Box(Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
            NavigationGraph(navController = navController)
        }
    }
}