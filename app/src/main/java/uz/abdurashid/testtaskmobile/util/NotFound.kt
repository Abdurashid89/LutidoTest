package uz.abdurashid.testtaskmobile.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.ui.theme.suseFamily

@Composable
fun NotFound(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {

        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier.padding(16.dp),
                text = "Ничего не найдено",
                fontFamily = suseFamily,
                color = androidx.compose.ui.graphics.Color.Black
            )

            Image(
                modifier = modifier.size(52.dp),
                painter = painterResource(id = R.drawable.not_found),
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NotFound_Preview() {
    NotFound()
}