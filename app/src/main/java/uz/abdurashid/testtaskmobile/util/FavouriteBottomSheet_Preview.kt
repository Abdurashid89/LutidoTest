package uz.abdurashid.testtaskmobile.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.ui.theme.TestTaskMobileTheme

@Preview(showBackground = true)
@Composable
fun FavouriteBottomSheet_Preview() {
    TestTaskMobileTheme {

        Box(modifier = Modifier.fillMaxSize()){
            FavouriteBottomSheet(
                address = Address(
                    0, "Hilton", "Mustaqillik",
                    "12"
                ),
                onDismissRequest = { },
                onSelectedAddress = { }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetScaffold_Preview() {
    MaterialTheme {
        BottomSheetScaffold(
            sheetContent = {
                FavouriteBottomSheet(
                    address = Address(
                        0, "Hilton", "Mustaqillik",
                        "12"
                    ),
                    onDismissRequest = { },
                    onSelectedAddress = { }
                )
            },
            sheetPeekHeight = 0.dp
        ) {
            // Main content
            Text("Main content here")
        }
    }
}
