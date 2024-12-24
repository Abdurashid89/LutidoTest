package uz.abdurashid.testtaskmobile.screens.map

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.geometry.BoundingBox
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.model.database.AddressDao
import uz.abdurashid.testtaskmobile.model.database.AddressDatabase
import uz.abdurashid.testtaskmobile.model.database.DatabaseInstance

data class MapState(
    val isMapLoaded: Boolean = false,
    val query: String = "",
    val geoObjects: List<GeoObject> = emptyList(),
    val showAddressBottomSheet: Boolean = false,
    val showFavouriteBottomSheet: Boolean = false,
    val showFavouriteDialog: Boolean = false,
    val addresses: ArrayList<Address> = arrayListOf(),
    val point: Point = Point(41.2995, 69.2401),
    val currentAddress: Address? = null,
    val showSearchBar: Boolean = true,
    val zoom: Float = 12.0f,
    val tilt: Float = 10.0f,
)

class MapViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MapState())
    val uiState: StateFlow<MapState> = _uiState.asStateFlow()
    private var database: AddressDatabase? = null
    private var addressDao: AddressDao? = null
    private var oldList = arrayListOf<Address>()


    fun initDatabase(context: Context) {
        database = DatabaseInstance.getDatabase(context)
        addressDao = database?.getAddressDao()
    }

    fun setZoom(zoom: Float = 12.0f) {
        _uiState.update { it.copy(zoom = zoom) }
    }

    fun setTilt(tilt: Float = 10.0f) {
        _uiState.update { it.copy(tilt = tilt) }
    }

    fun showAddressBottomSheet(show: Boolean = false) {
        if (!show) {
            oldList.clear()
            oldList.addAll(_uiState.value.addresses)
            _uiState.value.addresses.clear()
            _uiState.update { it.copy(query = "") }
        }
        _uiState.update { it.copy(showAddressBottomSheet = show, showSearchBar = !show) }
    }

    fun showSearchBar(show: Boolean = true) {
        _uiState.update { it.copy(showSearchBar = show) }
    }

    fun showFavoriteBottomSheet(show: Boolean = true) {
        _uiState.update { it.copy(showFavouriteBottomSheet = show) }
    }

    fun showFavoriteDialog(show: Boolean = true) {
        _uiState.update { it.copy(showFavouriteDialog = show) }
        if (!show) {
            setTilt()
            setZoom()
        }
    }

    fun updatePoint(point: Point) {
        _uiState.update { it.copy(point = point) }
    }

    fun addFavourite(address: Address) {
        showFavoriteDialog(false)
        setTilt()
        setZoom()
        viewModelScope.launch(Dispatchers.IO) {
            addressDao?.apply {
                insert(address)
            }
        }
    }

    fun addCurrentAddress(currentAddress: Address?) {
        _uiState.update { it.copy(currentAddress = currentAddress) }
    }

    fun addAddresses(address: Address) {
        _uiState.value.addresses.add(address)
    }

    fun initAddress() {
        _uiState.value.addresses.clear()
        _uiState.value.addresses.addAll(oldList)
        _uiState.update { it.copy(showAddressBottomSheet = true) }
        oldList.clear()
    }

    private val searchManager: SearchManager by lazy {
        SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)
    }

    fun searchByAddress(query: String, boundingBox: BoundingBox?) {
        _uiState.update { it.copy(query = query) }

        viewModelScope.launch {
            val geometry = boundingBox?.let { Geometry.fromBoundingBox(it) }
            val options = SearchOptions()

            _uiState.debounce(500)
                .filter { state ->
                    state.query.isNotEmpty()
                }
                .collectLatest { state ->
                    if (geometry != null) {
                        searchManager.submit(
                            state.query,
                            geometry,
                            options,
                            object : Session.SearchListener {
                                override fun onSearchResponse(response: Response) {
                                    _uiState.update { it.copy(geoObjects = response.collection.children.mapNotNull { result -> result.obj }) }
                                    if (uiState.value.geoObjects.isNotEmpty()) {
                                        _uiState.value.addresses.clear()
                                        uiState.value.geoObjects.forEachIndexed { index, geoObject ->
                                            val name = geoObject.name // Manzilning nomi
                                            val descriptionText =
                                                geoObject.descriptionText // Manzilning nomi
                                            // Manzilning nomi
                                            val point =
                                                geoObject.geometry.firstOrNull()?.point // Koordinatalar
                                            addAddresses(
                                                Address(
                                                    houseName = name,
                                                    house = descriptionText,
                                                    street = "aref",
                                                    latitude = point?.latitude ?: 41.2995,
                                                    longitude = point?.longitude ?: 69.2401
                                                )
                                            )
                                            showAddressBottomSheet(true)
                                            println("onResultQuery123: ${geoObject.name} $index")
                                        }
                                    }

                                }

                                override fun onSearchError(p0: com.yandex.runtime.Error) {

                                }

                            })
                    }
                }
        }
    }

    fun getFavouriteById(favouriteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            addressDao?.apply {
                getAddressById(favouriteId)
                    .collect { address ->
                        _uiState.update { it.copy(currentAddress = address) }
                        setZoom(18f)
                        setTilt(20f)

                        println("favouriteId123567: $address")
                        updatePoint(
                            Point(
                                address?.latitude ?: 41.2995,
                                address?.longitude ?: 69.2401
                            )
                        )
                    }
            }
        }
    }
}
