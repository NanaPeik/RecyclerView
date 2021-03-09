package ge.tsu.android.myapplication.recycle

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListDataManager(private val context: Context) {

    private val gson = Gson()

    fun add(list: List<RecycleViewItem>) {
        val sharedPreferences = getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(ExtraKeys.SHARED_PREFERENCES_KEY, gson.toJson(list))
        sharedPreferences.apply()
    }

    fun readList(): ArrayList<RecycleViewItem>? {
        val sharedPreferences = getDefaultSharedPreferences(context)

        val data = sharedPreferences.getString(ExtraKeys.SHARED_PREFERENCES_KEY, "")
        val type = object : TypeToken<ArrayList<RecycleViewItem>>() {}.type
        return gson.fromJson(data, type)
    }
}