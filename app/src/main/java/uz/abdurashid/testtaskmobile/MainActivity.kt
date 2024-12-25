package uz.abdurashid.testtaskmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.yandex.mapkit.MapKitFactory
import uz.abdurashid.testtaskmobile.screens.main.MainScreen
import uz.abdurashid.testtaskmobile.ui.theme.TestTaskMobileTheme
import uz.abdurashid.testtaskmobile.util.ChangeStatusBarColor

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val navController = rememberNavController()
            TestTaskMobileTheme {
                ChangeStatusBarColor(Color.White)
                MainScreen(navController)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }
}