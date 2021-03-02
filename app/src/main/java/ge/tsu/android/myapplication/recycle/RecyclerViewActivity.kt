package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R
import kotlin.random.Random
import kotlin.random.nextUBytes

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coordinator_layout)
        listsRecyclerView=findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager=LinearLayoutManager(this)
        listsRecyclerView.adapter= ListSelectionRecyclerViewAdapter()

    }
    fun addItemInListView(view: View): String {
        var random = Random
        var finalString = ""
        var range = random.nextInt(10,20)
        var item = 0
        while (item < range){
            var char = random.nextInt(97,122).toChar()
            finalString += char
            item++
        }
        return finalString
    }
}