@file:Suppress("DEPRECATION")

package uz.abdurashid.testtaskmobile.screens.map

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.BoundingBox
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import kotlinx.coroutines.delay
import showMessage
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.screens.map.components.AddFavouriteDialog
import uz.abdurashid.testtaskmobile.screens.map.components.AnimatedMarker
import uz.abdurashid.testtaskmobile.screens.map.components.SearchBar
import uz.abdurashid.testtaskmobile.util.AddressBottomSheet
import uz.abdurashid.testtaskmobile.util.FavouriteBottomSheet
import uz.abdurashid.testtaskmobile.util.PreferencesHelper

@Preview(showBackground = true)
@Composable
fun Map_Preview(modifier: Modifier = Modifier) {
    YandexMarker(MapViewModel())
}

@Composable
fun YandexMarker(
    viewModel: MapViewModel,
    context: Context = LocalContext.current,
    pref: PreferencesHelper = PreferencesHelper(context)
) {

    val id = pref.getLongValue("id", -1)
    if (id > -1L) {
        viewModel.getFavouriteById(id)
    }

    DisposableEffect(Unit) {
        onDispose {
            pref.saveLongValue("id", -1)
        }
    }

    println("favouriteId123: $id")

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isScrolledMap by rememberSaveable { mutableStateOf(false) }
    viewModel.initDatabase(context)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
                    .clickable { },
                onClick = {
                    viewModel.updatePoint(Point(
                        41.2995, 69.2401
                    ))
                    viewModel.setTilt()
                    viewModel.setZoom()
                },
                containerColor = Color.White,
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.share_location),
                    contentDescription = null,
                )
            }
        }
    ) {
        it.calculateTopPadding()
        AndroidView(
            factory = { ctx ->

                MapView(ctx).apply {

//                    map.mapObjects.addTapListener { mapObject, point ->
//                        // Mapdagi ob'ekt bosilganda ishlatilishi mumkin
//                        println("Tapped point: ${point.latitude}, ${point.longitude}")
//                        true
//                    }

                    map.apply {
                        addCameraListener {
                                currentMap,
                                cameraPosition,
                                reason,
                                finished,
                            ->
                            println("onCameraPositionChanged123: $finished ")

                            isScrolledMap = !finished

                            if (id > -1L) {
                                viewModel.setZoom(18f)
                                viewModel.setTilt(20f)
                                viewModel.updatePoint(Point(
                                    uiState.point.latitude,
                                    uiState.point.longitude
                                ))
                            } else {
                                viewModel.updatePoint(cameraPosition.target)
                            }
                        }
                        move(
                            CameraPosition(
                                Point(
                                    uiState.point.latitude,
                                    uiState.point.longitude
                                ),
                                14.0f, 10.0f, 10.0f
                            )
                        )

                    }
                }
            },
            update = { view ->
                view.map.move(
                    CameraPosition(
                        Point(
                            uiState.point.latitude,
                            uiState.point.longitude
                        ),
                        uiState.zoom, 10.0f, uiState.tilt
                    ),
                    Animation(Animation.Type.SMOOTH, 0.5F),
                    null
                )
            },
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            if (uiState.showSearchBar) {
                SearchBar(query = uiState.query) { newQuery ->
                    viewModel.searchByAddress(
                        newQuery, BoundingBox(
                            Point(41.1870, 69.1221), // Janubi-g'arb nuqta (SW corner)
                            Point(41.3890, 69.3600)
                        )
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedMarker(isSelected = isScrolledMap)
        }

        if (uiState.showFavouriteDialog) {
            AddFavouriteDialog(
                onDismissRequest = {
                    viewModel.showFavoriteDialog(false)
                },
                onConfirm = {
                    uiState.currentAddress?.let { it1 -> viewModel.addFavourite(it1) }
                    showMessage(context, "Адрес добавлен в избранное")
                },
                onCancel = {
                    viewModel.showFavoriteDialog(false)
                    viewModel.showFavoriteBottomSheet(true)
                }
            )
        }

        if (uiState.showFavouriteBottomSheet) {
            uiState.currentAddress?.let { it1 ->
                FavouriteBottomSheet(address = it1,
                    onDismissRequest = {
                        viewModel.showFavoriteBottomSheet(false)
                        viewModel.setTilt()
                        viewModel.setZoom()
                    },
                    onCancel = {
                        viewModel.showFavoriteBottomSheet(false)
                        viewModel.initAddress()
                        viewModel.setTilt()
                        viewModel.setZoom()
                    }
                ) { address: Address ->
                    viewModel.addCurrentAddress(address)
                    viewModel.showFavoriteDialog(true)
                    viewModel.showFavoriteBottomSheet(false)
                }
            }
        }

        if (uiState.showAddressBottomSheet) {
            AddressBottomSheet(
                list = uiState.addresses,
                onDismissRequest = {
                    viewModel.showAddressBottomSheet(false)
                },
                query = uiState.query,
                onQueryChanged = { newQuery ->
                    viewModel.searchByAddress(
                        newQuery,
                        BoundingBox(
                            Point(41.1870, 69.1221), // Janubi-g'arb nuqta (SW corner)
                            Point(41.3890, 69.3600)
                        )
                    )
                },
                onSelectedAddress = { newAddress ->
                    viewModel.showFavoriteBottomSheet()
                    viewModel.addCurrentAddress(newAddress)
                    viewModel.setTilt(20.0f)
                    viewModel.setZoom(18.0f)
                    viewModel.showAddressBottomSheet(false)
                    viewModel.updatePoint(Point(newAddress.latitude, newAddress.longitude))
                }
            )
            LaunchedEffect(key1 = uiState.showSearchBar) {
                delay(1_500)
                viewModel.showSearchBar(false)
            }
        }

    }

}