package uz.abdurashid.testtaskmobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val houseName: String?,
    val street: String?,
    val house: String?,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
