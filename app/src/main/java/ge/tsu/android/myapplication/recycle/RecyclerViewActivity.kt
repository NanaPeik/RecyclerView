package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coordinator_layout)
        listsRecyclerView=findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager=LinearLayoutManager(this)
        listsRecyclerView.adapter= ListSelectionRecyclerViewAdapter()

    }

}