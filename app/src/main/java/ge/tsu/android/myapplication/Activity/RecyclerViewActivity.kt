package ge.tsu.android.myapplication.Activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.Activity.SettingsActivity.Companion.SHOW_COMPLETED_ELEMENTS
import ge.tsu.android.myapplication.Activity.SettingsActivity.Companion.SORT_BY_TITLE
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.data.RecycleViewItem
import ge.tsu.android.myapplication.databinding.ActivityRecyclerviewBinding
import ge.tsu.android.myapplication.fragments.TextFragment
import ge.tsu.android.myapplication.keys.ExtraKeys
import ge.tsu.android.myapplication.recycle.ListSelectionRecyclerViewAdapter
import ge.tsu.android.myapplication.storage.ListDataManager
import ge.tsu.android.myapplication.storage.SettingsDataManager
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity(R.layout.activity_recyclerview) {

    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var adapter: ListSelectionRecyclerViewAdapter

    companion object {
        var showChecked: Boolean = false
    }

    val listDataManager: ListDataManager = ListDataManager(this)
    val settingsDataManager: SettingsDataManager = SettingsDataManager(this)

    private var listTitles = ArrayList<RecycleViewItem>()


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
            val title = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA)
            val detiles = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA_DETILES)
            title?.let {

                var itemTitle = it
                detiles?.let {
                    var rvi = RecycleViewItem(
                        listTitles.size + 1,
                        itemTitle,
                        false,
                        it,
                        Date()
                    )
                    listTitles.add(rvi)
                }

                binding.fab.isClickable = true

                listDataManager.add(listTitles)
                adapter.notifyItemInserted(listTitles.size)
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


    override fun onResume() {

        getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(
            { sharedPreferences, key ->

            }
        )
        super.onResume()
        var listTitlesChecked = ArrayList<RecycleViewItem>()
        if (settingsDataManager.readSettingsData(SHOW_COMPLETED_ELEMENTS)) {

            for (item in listTitles) {
                if (item.isChecked) {
                    listTitlesChecked.add(item)
                }
            }

            showChecked = !showChecked
        } else {
            listTitlesChecked = listTitles
        }
        if (settingsDataManager.readSettingsData(SORT_BY_TITLE)) {
            var sortedList = listTitlesChecked.sortedWith(compareBy({
                it.itemText
            }))
            adapter = ListSelectionRecyclerViewAdapter(sortedList.toMutableList())
            binding.listsRecyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            var sortedList = listTitlesChecked.sortedWith(compareBy({
                it.date
            }))
            adapter = ListSelectionRecyclerViewAdapter(sortedList.toMutableList())
            binding.listsRecyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        val searchMenuItem = menu.findItem(R.id.item_search)
        searchMenuItem.actionView?.let {
            var searchView = searchMenuItem.actionView as SearchView

            val searchManager = getSystemService(SEARCH_SERVICE)
                    as SearchManager

            searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName)
            )
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }

            })
        }
        return true
    }

    //  override fun onSearchRequested(): Boolean {
//    return super.onSearchRequested()
//  }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }

        return super.onOptionsItemSelected(item)
    }
}