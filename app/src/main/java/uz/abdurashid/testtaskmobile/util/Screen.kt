package uz.abdurashid.testtaskmobile.util

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.screens.favourite.FavouriteScreen
import uz.abdurashid.testtaskmobile.screens.map.MapViewModel
import uz.abdurashid.testtaskmobile.screens.map.YandexMarker
import uz.abdurashid.testtaskmobile.screens.profile.ProfileSettingsScreen
import uz.abdurashid.testtaskmobile.screens.profile.SettingsItem

sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
    data object Favourite : Screen("favourite", "Favourite", R.drawable.favourite)
    data object Map : Screen("map", "Map", R.drawable.location_filled)
    data object Contacts : Screen("contacts", "Contacts", R.drawable.ic_person)
}

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Map.route) {
        composable(Screen.Favourite.route) {
            FavouriteScreen {
                navController.navigate(Screen.Map.route)
            }
        }
        composable(
            route = Screen.Map.route,//"${Screen.Map.route}/{favouriteId}",
//            arguments = listOf(navArgument("favouriteId") {
//                type = NavType.LongType
//            })
        ) { backStackEntry ->
          //  val favouriteId = backStackEntry.arguments?.getLong("favouriteId") ?: 0L
            YandexMarker(MapViewModel())
        }
        composable(Screen.Contacts.route) {
            val item1 = SettingsItem("Language", "Select language", Icons.Default.Edit)
            val item2 = SettingsItem("Support", "Get help", Icons.Default.ThumbUp)
            val item3 = SettingsItem("Share", "Share to friends", Icons.Default.Share)
            val list = listOf(item1, item2, item3)
            ProfileSettingsScreen(
                "Марк Цукерберг",
                "taylor@myownpersonaldomain.com",
                onEditProfileClick = {},
                settingsItems = list,
                onSettingClick = {}
            )
        }

    }
}