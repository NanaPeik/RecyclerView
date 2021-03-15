package ge.tsu.android.myapplication.storage

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ge.tsu.android.myapplication.data.RecycleViewItem
import ge.tsu.android.myapplication.keys.ExtraKeys

class ListDataManager(private val context: Context) {
    val gson = Gson()
    fun add(list: List<RecycleViewItem>) {
        val sharedPreferences = getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(ExtraKeys.SHARED_PREFERENCES_KEY, gson.toJson(list))
        sharedPreferences.apply()
    }

    fun readList(): ArrayList<RecycleViewItem>? {
        val sharedPreferences = getDefaultSharedPreferences(context)
        val data = sharedPreferences.getString(ExtraKeys.SHARED_PREFERENCES_KEY, "")
        val type = object : TypeToken<ArrayList<RecycleViewItem>>() {}.getType()
        return gson.fromJson(data, type)
    }
}