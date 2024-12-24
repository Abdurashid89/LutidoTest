package uz.abdurashid.testtaskmobile.screens.favourite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.model.database.AddressDatabase
import uz.abdurashid.testtaskmobile.model.database.DatabaseInstance

data class FavouriteUiState(
    val favourite: List<Address> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

class FavouriteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FavouriteUiState())
    val uiState: StateFlow<FavouriteUiState> = _uiState.asStateFlow()

    private var database: AddressDatabase? = null
    private val addressDao get() = database?.getAddressDao()

    fun initDatabase(context: Context) {
        database = DatabaseInstance.getDatabase(context)
        getAllAddress()
    }

    private fun getAllAddress(){
        viewModelScope.launch(Dispatchers.IO) {
            addressDao?.let { dao ->
                dao.getAllAddresses().collect{ addresses ->
                    _uiState.update { it.copy(favourite = addresses) }
                }
            }
        }
    }
}

object Favourite {
    private val address = Address(
        house = "2",
        street = "ул. Узбекистон Овози",
        houseName = "Le Grande Plaza Hotel",
        latitude = 1.1, longitude = 2.2
    )
    val list = listOf(address, address, address)


}