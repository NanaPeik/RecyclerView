package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.CoordinatorLayoutBinding

class RecyclerViewActivity : AppCompatActivity(R.layout.coordinator_layout) {
        private lateinit var binding: CoordinatorLayoutBinding

        var listTitles = arrayListOf("Shopping List", "Chores", "Android Tutorials")


    override fun onCreate(savedInstanceState: Bundle?) {
                supportActionBar?.hide()

        super.onCreate(savedInstanceState)

        binding = CoordinatorLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listsRecyclerview.adapter = ListSelectionRecyclerViewAdapter(listTitles)
//        binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)
        val viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        viewModel.currentString.observe(this, Observer { item ->
            listTitles.add(item)
            Log.d("item-------------",item)
            binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)
        })
//
            if (savedInstanceState == null) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<ExampleFragment>(R.id.fragment_container_view)
                }
            }

    }
//
//    companion object {
//        lateinit var binding: CoordinatorLayoutBinding
//
//        var listTitles = arrayListOf("Shopping List", "Chores", "Android Tutorials")
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.hide()
//
//        super.onCreate(savedInstanceState)
//        binding = CoordinatorLayoutBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
//        binding.listsRecyclerview.adapter = ListSelectionRecyclerViewAdapter(listTitles)
//
//        binding.fab.setOnClickListener {
//
//        }
//    }


//    fun addItemInListView(view: View) {
//        var random = Random
//        var finalString = ""
//        var range = random.nextInt(10,20)
//        var item = 0
//        while (item < range){
//            var char = random.nextInt(97,122).toChar()
//            finalString += char
//            item++
//        }
//        listTitles.add(finalString)
//        binding.listsRecyclerview.adapter?.notifyDataSetChanged()
//        binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)
//    }
}