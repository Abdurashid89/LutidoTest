package uz.abdurashid.testtaskmobile.util

import android.content.Context

class PreferencesHelper(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("test_prefs", Context.MODE_PRIVATE)

    fun saveLongValue(key: String, value: Long) {
        with(sharedPreferences.edit()) {
            putLong(key, value)
            apply()
        }
    }

    fun getLongValue(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }
}
