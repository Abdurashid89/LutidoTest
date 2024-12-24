package uz.abdurashid.testtaskmobile.screens.favourite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.abdurashid.testtaskmobile.ui.theme.robotoFamily

@Composable
fun FavouriteTopBar(
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = title, fontSize = 24.sp, color = Color.Black,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold

        )
    }
}
