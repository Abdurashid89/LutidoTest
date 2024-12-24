package uz.abdurashid.testtaskmobile.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.abdurashid.testtaskmobile.model.Address

@Database(entities = [Address::class], version = 1, exportSchema = true)
abstract class AddressDatabase : RoomDatabase() {
    abstract fun getAddressDao(): AddressDao
}

object DatabaseInstance {

    @Volatile
    private var INSTANCE: AddressDatabase? = null

    fun getDatabase(context: Context): AddressDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AddressDatabase::class.java,
                "address_database"
            ).build()
            INSTANCE = instance
            instance

        }

    }

}