package ge.tsu.android.myapplication.storage

import android.content.Context
import androidx.preference.PreferenceManager


class SettingsDataManager(private val context: Context) {
    fun addSettings(key: String, isChecked: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(key, isChecked.toString())
        sharedPreferences.apply()
    }

    fun readSettingsData(key: String): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val data = sharedPreferences.getString(key, "")
        return data.toBoolean()
    }
}