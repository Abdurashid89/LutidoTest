package uz.abdurashid.testtaskmobile.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangeStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    //val useDarkIcons = MaterialTheme.colorScheme.isLight // Rangga qarab ikonalarni moslashtirish

    SideEffect {
        systemUiController.setStatusBarColor(
            color = color
        )
    }
}
