package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.ActivityRecyclerviewBinding

class RecyclerViewActivity : AppCompatActivity(R.layout.activity_recyclerview) {
    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var adapter: ListSelectionRecyclerViewAdapter

    val listDataManager: ListDataManager = ListDataManager(this)

    private var listTitles = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listTitles = listDataManager.readList() ?: ArrayList()
        adapter = ListSelectionRecyclerViewAdapter(listTitles)


        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listsRecyclerview.adapter = adapter

        supportFragmentManager.setFragmentResultListener(
            ExtraKeys.FRAGMENT_REQUEST_KEY,
            this
        ) { _, bundle ->
            val result = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA)
            result?.let {
                listTitles.add(it)
                listDataManager.add(listTitles)
                adapter.notifyItemInserted(listTitles.size)
            }
        }

        binding.fab.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TextFragment>(R.id.fragment_container_view)
            }
        }
    }
}