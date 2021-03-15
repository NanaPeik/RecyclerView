package ge.tsu.android.myapplication.storage

import android.content.Context
import androidx.preference.PreferenceManager


class SettingsDataManager(private val context: Context) {
    fun addSettings(key: String, isChecked: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putBoolean(key, isChecked)
        sharedPreferences.apply()
    }

    fun readSettingsData(key: String): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val data = sharedPreferences.getBoolean(key, false)
        return data
    }
}