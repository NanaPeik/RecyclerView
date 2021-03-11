package ge.tsu.android.myapplication.recycle

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
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
}