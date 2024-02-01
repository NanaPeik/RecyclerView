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
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.Activity.SettingsActivity.Companion.SHOW_COMPLETED_ELEMENTS
import ge.tsu.android.myapplication.Activity.SettingsActivity.Companion.SORT_BY_TITLE
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.adapter.ListSelectionRecyclerViewAdapter
import ge.tsu.android.myapplication.adapter.onClickInterface
import ge.tsu.android.myapplication.data.RecycleViewItem
import ge.tsu.android.myapplication.databinding.ActivityRecyclerviewBinding
import ge.tsu.android.myapplication.fragments.TextFragment
import ge.tsu.android.myapplication.keys.ExtraKeys
import ge.tsu.android.myapplication.storage.ListDataManager
import ge.tsu.android.myapplication.storage.SettingsDataManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity(R.layout.activity_recyclerview) {

    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var adapter: ListSelectionRecyclerViewAdapter


    companion object {
        var showChecked = false
        lateinit var onclickInterface: onClickInterface
    }

    val listDataManager: ListDataManager = ListDataManager(this)
    private val settingsDataManager: SettingsDataManager = SettingsDataManager(this)

    private var listTitles = ArrayList<RecycleViewItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listTitles = listDataManager.readList() ?: ArrayList()

        onclickInterface = object : onClickInterface {
            override fun onClick(position: Int, res: Boolean) {
                listTitles[position - 1].isChecked = res
                listDataManager.add(listTitles)
            }

            override fun showDetails(position: Int) {
                val intent = Intent(this@RecyclerViewActivity, DetailsActivity::class.java)
                intent.putExtra(ExtraKeys.INTENT_ITEM_NUMBER, listTitles[position].itemNumber)
                intent.putExtra(ExtraKeys.INTENT_ITEM_TITLE, listTitles[position].itemText)
                intent.putExtra(ExtraKeys.INTENT_ITEM_DETAILS, listTitles[position].details)
                intent.putExtra(ExtraKeys.INTENT_ITEM_DATE, listTitles[position].date.toString())
                intent.putExtra(ExtraKeys.INTENT_ITEM_CHECKED, listTitles[position].isChecked)
                startActivity(intent)
            }

        }

        adapter = ListSelectionRecyclerViewAdapter(listTitles, onclickInterface)

        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listsRecyclerview.adapter = adapter


        supportFragmentManager.setFragmentResultListener(
            ExtraKeys.FRAGMENT_REQUEST_KEY,
            this
        ) { _, bundle ->

            val title = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA)
            val detiles = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA_DETAILS)
            title?.let { it ->

                val currentDate = LocalDateTime.now().format(
                    DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.LONG, FormatStyle.SHORT
                    )
                )
                val itemTitle = it.trim()
                detiles?.let { it ->
                    val rvi = RecycleViewItem(
                        listTitles.size + 1,
                        itemTitle,
                        false,
                        it.trim(),
                        currentDate
                    )
                    listTitles.add(rvi)
                }


                listDataManager.add(listTitles)
                onResume()
                adapter.notifyItemInserted(listTitles.size)
            }
        }


        binding.fab.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                add<TextFragment>(R.id.fragment_container_view)

            }
        }

        //delete RecycleView form details activity
        val intent = intent

        intent.getStringExtra(ExtraKeys.INTENT_DELETE_ITEM)?.let {
            val index = intent.getStringExtra(ExtraKeys.INTENT_DELETE_ITEM)?.toInt()
            var deleteIndex = 0
            for (item in listTitles) {
                if (item.itemNumber == index) {
                    deleteIndex = listTitles.indexOf(item)
                }
            }
            listTitles.removeAt(deleteIndex)

            listDataManager.add(listTitles)
            adapter.notifyDataSetChanged()
        }

        //edit RecycleView form details activity
        intent.getStringExtra(ExtraKeys.PREVIOUS_NUMBER)?.let {
            var editObject = RecycleViewItem(
                intent.getStringExtra(ExtraKeys.PREVIOUS_NUMBER)!!.toInt(),
                intent.getStringExtra(ExtraKeys.PREVIOUS_TITLE).toString(),
                intent.getStringExtra(ExtraKeys.PREVIOUS_CHECKED).toBoolean(),
                intent.getStringExtra(ExtraKeys.PREVIOUS_DETAILS).toString(),
                intent.getStringExtra(ExtraKeys.PREVIOUS_DATE).toString()
            )
            var editIndex = 0
            for (item in listTitles) {
                if (item == editObject) {
                    editIndex = listTitles.indexOf(item)
                }
            }

            listTitles[editIndex].itemText =
                intent.getStringExtra(ExtraKeys.EDITED_TITLE).toString()
            listTitles[editIndex].details =
                intent.getStringExtra(ExtraKeys.EDITED_DETAILS).toString()
            listTitles[editIndex].date = intent.getStringExtra(ExtraKeys.EDITED_DATE).toString()

            listDataManager.add(listTitles)

            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showChecked = settingsDataManager.readSettingsData(SHOW_COMPLETED_ELEMENTS)
        val sortByTitle = settingsDataManager.readSettingsData(SORT_BY_TITLE)

        val list = listTitles.filter {
            if (showChecked) {
                true
            } else {
                !it.isChecked
            }
        }.sortedWith(compareBy {
            if (sortByTitle) {
                it.itemText
            } else {
                it.date
            }
        })

        adapter = ListSelectionRecyclerViewAdapter(list.toMutableList(), onclickInterface)
        binding.listsRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        val searchMenuItem = menu.findItem(R.id.item_search)
        searchMenuItem.actionView?.let {
            val searchView = searchMenuItem.actionView as SearchView

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }

        return super.onOptionsItemSelected(item)
    }
}