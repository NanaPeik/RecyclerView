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

    private val listDataManager: ListDataManager = ListDataManager(this)

    private var listTitles = mutableListOf<RecycleViewItem>()
    private var allItems = mutableListOf<RecycleViewItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allItems = listDataManager.readList() ?: ArrayList()
        listTitles.addAll(allItems)
        adapter = ListSelectionRecyclerViewAdapter(listTitles)

        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listsRecyclerview.adapter = adapter

        binding.switcher.setOnCheckedChangeListener { _, isChecked ->
            listTitles.clear()
            listTitles.addAll(
                allItems.filter { it.isChecked == isChecked }
            )
            adapter.notifyDataSetChanged()
        }

        supportFragmentManager.setFragmentResultListener(
            ExtraKeys.FRAGMENT_REQUEST_KEY,
            this
        ) { _, bundle ->
            val result = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA)
            result?.let {

                val rvi = RecycleViewItem(
                    allItems.size + 1,
                    it,
                    false
                )
                allItems.add(rvi)

                binding.fab.isClickable = true

                listDataManager.add(allItems)
                if (!adapter.showChecked) {
                    listTitles.add(rvi)
                    adapter.notifyItemInserted(listTitles.size)
                }
            }
        }

        binding.fab.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TextFragment>(R.id.fragment_container_view)

                binding.fab.isClickable = false
            }
        }
    }
}