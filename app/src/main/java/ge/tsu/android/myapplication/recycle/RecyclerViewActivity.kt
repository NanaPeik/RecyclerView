package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView

    var listTitles = arrayListOf<String>("Shopping List", "Chores", "Android Tutorials")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coordinator_layout)
        listsRecyclerView=findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager=LinearLayoutManager(this)
        listsRecyclerView.adapter= ListSelectionRecyclerViewAdapter(listTitles)

    }
    fun addItemInListView(view: View) {
        var random = Random
        var finalString = ""
        var range = random.nextInt(10,20)
        var item = 0
        while (item < range){
            var char = random.nextInt(97,122).toChar()
            finalString += char
            item++
        }
        listTitles.add(finalString)
//        listsRecyclerView.adapter?.notifyDataSetChanged()
        listsRecyclerView.adapter?.notifyItemChanged(listTitles.size-1)

    }

}