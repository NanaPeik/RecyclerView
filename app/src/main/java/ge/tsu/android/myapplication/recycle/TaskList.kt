package ge.tsu.android.myapplication.recycle

data class TaskList(
    val name: String,
    val tasks: ArrayList<String> = ArrayList()
)