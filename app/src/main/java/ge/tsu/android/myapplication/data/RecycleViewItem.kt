package ge.tsu.android.myapplication.data

data class RecycleViewItem(
   var itemNumber: Int = 0,
   var itemText: String,
   var isChecked: Boolean = false,
   var details: String,
   var date: String
)