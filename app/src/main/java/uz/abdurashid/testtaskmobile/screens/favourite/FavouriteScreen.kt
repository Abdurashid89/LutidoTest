package uz.abdurashid.testtaskmobile.screens.favourite

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.screens.favourite.components.FavouriteItem
import uz.abdurashid.testtaskmobile.screens.favourite.components.FavouriteTopBar
import uz.abdurashid.testtaskmobile.ui.theme.Favourite_Back_Color
import uz.abdurashid.testtaskmobile.util.FavouriteItemShimmer
import uz.abdurashid.testtaskmobile.util.NotFound
import uz.abdurashid.testtaskmobile.util.PreferencesHelper

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = FavouriteViewModel(),
    context: Context = LocalContext.current,
    pref : PreferencesHelper = PreferencesHelper(context),
    onFavouriteClick: (Address) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.initDatabase(context)
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isLoading) {
        delay(2000)
        isLoading = false
    }

    Column(
        modifier
            .fillMaxSize()
            .background(Favourite_Back_Color)
    ) {
        FavouriteTopBar("Мои адреса")

        Spacer(modifier = modifier.padding(top = 16.dp))

        if (isLoading) {
            LazyColumn {
                items(10) { index ->
                    FavouriteItemShimmer()
                }
            }
        } else {
            if (uiState.favourite.isEmpty()) {
                NotFound()
            } else {
                LazyColumn {
                    items(uiState.favourite.size) { index ->
                        FavouriteItem(
                            address = uiState.favourite[index],
                            icon = R.drawable.location_filled,
                            onClick = {
                                pref.saveLongValue("id",it.id)
                                onFavouriteClick(it)
                            }
                        )
                    }
                }
            }
        }
    }
}