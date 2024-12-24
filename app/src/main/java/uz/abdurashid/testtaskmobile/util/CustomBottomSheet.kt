package uz.abdurashid.testtaskmobile.util

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.screens.favourite.components.FavouriteItem
import uz.abdurashid.testtaskmobile.screens.map.components.SearchBar
import uz.abdurashid.testtaskmobile.ui.theme.Color_Gray
import uz.abdurashid.testtaskmobile.ui.theme.Color_SearchBar
import uz.abdurashid.testtaskmobile.ui.theme.robotoFamily


@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    address: Address,
    onClick: (Address) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable { onClick(address) },

        ) {
        Column {
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location_filled),
                        contentDescription = "Location"
                    )
                }


                Column(
                    modifier
                        .background(Color.White)
                        .wrapContentSize()
                        .padding(start = 12.dp), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
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
                //

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(
                        text = "32m",
                        modifier = Modifier.padding(4.dp),
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .background(Color_SearchBar)
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteBottomSheet(
    address: Address,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit = {},
    onSelectedAddress: (Address) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = {
            scope.launch { sheetState.hide() }
            onDismissRequest()
            scope.launch { sheetState.show() }.invokeOnCompletion {}
        },
        sheetState = sheetState,
        dragHandle = { }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Divider(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color_SearchBar)
                    .height(4.dp)
                    .width(48.dp)
                    .padding(12.dp)
            )
            FavouriteItem(address = address,
                onClick = {
                    onSelectedAddress(address)
                },
                elevation = 0,
                icon = R.drawable.ic_close,
                onDismiss = { onCancel() })

            Box(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        RatingBar(
                            4,
                            onRatingChanged = {}
                        )

                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "517 оценок",
                            fontFamily = robotoFamily,
                            fontSize = 12.sp, color = Color(0xFFB0ABAB)
                        )

                    }

                    CustomButton(
                        "Добавить в избранное",
                        address,
                        onSelectedAddress
                    )

                }
            }


        }
    }
}

@Composable
fun CustomButton(
    text: String,
    address: Address,
    onSelectedAddress: (Address) -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(24.dp))
            .height(42.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF5BC250),
            contentColor = Color(0xFF5BC250)
        ),
        onClick = {
            onSelectedAddress(address)
        }) {
        Text(
            text = text,
            fontFamily = robotoFamily,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(
    list: ArrayList<Address>,
    onDismissRequest: () -> Unit,
    onSelectedAddress: (Address) -> Unit,
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = {
            scope.launch { sheetState.hide() }
            onDismissRequest()
            scope.launch { sheetState.show() }.invokeOnCompletion {}
        },
        sheetState = sheetState,
        dragHandle = { }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Divider(
                modifier = Modifier
                    .background(Color_Gray)
                    .width(54.dp)
                    .height(4.dp)
            )

            SearchBar(query = query) { newQuery ->
                onQueryChanged(newQuery)
            }

            LazyColumn {
                item {
                    Divider(
                        modifier = Modifier
                            .background(Color_SearchBar)
                            .fillMaxWidth()
                    )
                }
                items(list.size) { index ->
                    BottomSheetItem(address = list[index]) {
                        onSelectedAddress(list[index])
                    }
                }
            }

        }
    }
}