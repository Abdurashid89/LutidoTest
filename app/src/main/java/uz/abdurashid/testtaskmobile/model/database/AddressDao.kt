package uz.abdurashid.testtaskmobile.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.abdurashid.testtaskmobile.model.Address

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(address: Address)

    @Query("SELECT * FROM addresses WHERE id = :id")
    fun getAddressById(id: Long): Flow<Address?>


    @Query("UPDATE addresses SET houseName = :houseName, street = :street, house = :house , latitude = :latitude, longitude = :longitude   WHERE id = :id")
    fun update(
        houseName: String,
        street: String,
        house: String,
        id: Long,
        latitude: Double,
        longitude: Double,
    )

    @Query("DELETE FROM addresses WHERE id = :id")
    fun delete(id: Long)

    @Query("SELECT * FROM addresses")
    fun getAllAddresses(): Flow<List<Address>>
}