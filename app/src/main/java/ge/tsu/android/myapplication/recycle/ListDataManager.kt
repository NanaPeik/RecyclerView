package ge.tsu.android.myapplication.recycle

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import kotlin.collections.ArrayList

class ListDataManager(private val context: Context) {

    val gson=Gson()

    fun add(value: TaskList){
        val sharedPreferences= getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(value.name,gson.toJson(value))
        sharedPreferences.apply()
    }

    fun readList():TaskList{
        val sharedPreferences = getDefaultSharedPreferences(context)

        val data: String? = sharedPreferences.all.get(ExtraKeys.SHARED_PREFERENCES_KEY) as String?
        return gson.fromJson(data,TaskList::class.java)
    }

}