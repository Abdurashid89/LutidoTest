package uz.abdurashid.testtaskmobile.screens.favourite.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.screens.favourite.Favourite
import uz.abdurashid.testtaskmobile.ui.theme.robotoFamily

@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    address: Address,
    elevation: Int = 2,
    onClick: (Address) -> Unit = {},
    @DrawableRes icon: Int,
    onDismiss: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable { onClick(address) },
        elevation = CardDefaults.elevatedCardElevation(elevation.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = address.houseName ?: "",
                    modifier = Modifier.padding(4.dp),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal
                )
                Text(
                    text = "${address.house}",
                    modifier = Modifier.padding(4.dp),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    modifier = modifier
                        .size(24.dp)
                        .clickable {
                            onDismiss()
                        },
                    painter = painterResource(id = icon),
                    contentDescription = "Location"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Favourite_Preview() {
    FavouriteItem(address = Favourite.list[0], icon = R.drawable.location_filled)
}