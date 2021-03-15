package ge.tsu.android.myapplication.storage

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SettingsDataManager(private val context: Context) {
    val gson = Gson()
    fun addSettings(key: String, isChecked: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(key, gson.toJson(isChecked))
        sharedPreferences.apply()
    }

    fun readSettingsData(key: String): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val data = sharedPreferences.getString(key, "")
        val type = object : TypeToken<Boolean>() {}.getType()
        return gson.fromJson(data, type)
    }
}