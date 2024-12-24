import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun ShowMessage(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}
fun showMessage(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermissions(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionsState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(key1 = permissionsState.status) {
        when {
            permissionsState.status.isGranted -> {
                onPermissionGranted()
            }

            permissionsState.status.shouldShowRationale -> {
                // Foydalanuvchiga nima uchun ruxsat kerakligini tushuntiring
                Toast.makeText(context, "Location permission is required.", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                permissionsState.launchPermissionRequest()
            }
        }
    }
}

@Composable
fun OpenSettings() {
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    startActivity(context, intent, null)

}
