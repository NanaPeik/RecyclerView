package ge.tsu.android.myapplication.recycle

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.ArrayMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListDataManager<T>(private val sharedPreferencesKey: String, private val context: Context) {

  val gson = Gson()

  fun add(list: T) {
    val sharedPreferences = getDefaultSharedPreferences(context).edit()
    sharedPreferences.putString(sharedPreferencesKey, gson.toJson(list))
    sharedPreferences.apply()
  }

  fun readList(): ArrayList<RecycleViewItem>? {
    val sharedPreferences = getDefaultSharedPreferences(context)

    val data = sharedPreferences.getString(ExtraKeys.SHARED_PREFERENCES_KEY, "")
    val type = object : TypeToken<T>() {}.getType()
    return gson.fromJson(data, type)
  }

  fun addSettings(list: androidx.collection.ArrayMap<String, Boolean>) {
    val sharedPreferences = getDefaultSharedPreferences(context).edit()
    sharedPreferences.putString(ExtraKeys.SHARED_PREFERENCES_SETTINGS, gson.toJson(list))
    sharedPreferences.apply()
  }

  fun readSettings(): ArrayMap<String, Boolean>? {
    val sharedPreferences = getDefaultSharedPreferences(context)

    val data = sharedPreferences.getString(ExtraKeys.SHARED_PREFERENCES_SETTINGS, "")
    val type = object : TypeToken<ArrayMap<String, Boolean>>() {}.getType()
    return gson.fromJson(data, type)
  }
}