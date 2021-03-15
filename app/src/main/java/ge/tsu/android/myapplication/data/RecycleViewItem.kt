package ge.tsu.android.myapplication.data

import java.util.*

data class RecycleViewItem(
   var itemNumber: Int = 0,
   var itemText: String,
   var isChecked: Boolean = false,
   var detiles: String,
   var date: Date
)