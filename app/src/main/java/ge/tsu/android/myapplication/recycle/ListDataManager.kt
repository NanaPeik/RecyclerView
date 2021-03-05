package ge.tsu.android.myapplication.recycle

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(private val context: Context) {

    fun saveList(list: ArrayList<String>){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(ExtraKeys.SHARED_PREFERENCES_KEY,list.toHashSet())
        sharedPreferences.apply()
    }
    fun readList():ArrayList<String>{
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContexts = sharedPreferences.all
        val taskLists = ArrayList<String>()
        for (tasklist in sharedPreferencesContexts){
            val itemsHashSet = ArrayList(tasklist.value as HashSet<String>)
            taskLists.addAll(itemsHashSet)
        }
        return taskLists
    }
}