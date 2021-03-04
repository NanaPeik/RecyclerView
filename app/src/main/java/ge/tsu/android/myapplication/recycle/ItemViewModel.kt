package ge.tsu.android.myapplication.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
//    private val mutableSelectedItem = MutableLiveData<String>()
//    val selectedItem: LiveData<String> get() = mutableSelectedItem
//
//    fun selectItem(item: String) {
//        mutableSelectedItem.value = item
//    }
    val string=""
    val currentString: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}