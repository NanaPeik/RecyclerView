package ge.tsu.android.myapplication.recycle

import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.Settings
import ge.tsu.android.myapplication.databinding.ActivityRecyclerviewBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity(R.layout.activity_recyclerview) {

  private lateinit var binding: ActivityRecyclerviewBinding
  private lateinit var adapter: ListSelectionRecyclerViewAdapter

  companion object {
    var showChecked: Boolean = false
  }

  val listDataManager: ListDataManager = ListDataManager(this)

  private var listTitles = ArrayList<RecycleViewItem>()
  private lateinit var settingList: ArrayMap<String, Boolean>


  lateinit var list : ArrayList<String>


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
    setContentView(binding.root)

    listTitles = listDataManager.readList() ?: ArrayList()
    settingList = listDataManager.readSettings() ?: ArrayMap()

    adapter = ListSelectionRecyclerViewAdapter(listTitles)

    binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
    binding.listsRecyclerview.adapter = adapter


    binding.itemSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return false
      }

    })

    supportFragmentManager.setFragmentResultListener(
      ExtraKeys.FRAGMENT_REQUEST_KEY,
      this
    ) { _, bundle ->
      val title = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA)
      val detiles = bundle.getString(ExtraKeys.FRAGMENT_RESULT_EXTRA_DETILES)
      title?.let {

        var itemTitle = it
        detiles?.let{
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
    super.onResume()
    var listTitlesChecked = ArrayList<RecycleViewItem>()
    settingList?.let {
      if (it.getValue("showCompleted")) {

        for (item in listTitles) {
          if (item.isChecked) {
            listTitlesChecked.add(item)
          }
        }

        showChecked = !showChecked
      } else {
        listTitlesChecked = listTitles
      }
      if (it.getValue("shrtByTitle")) {
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

  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    if (item.itemId == R.id.settings) {
      val settingsIntent = Intent(this, Settings::class.java)
      startActivity(settingsIntent)
    }


    return super.onOptionsItemSelected(item)
  }
}