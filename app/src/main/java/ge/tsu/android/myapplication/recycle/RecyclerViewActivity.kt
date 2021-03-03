package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.CoordinatorLayoutBinding

class RecyclerViewActivity : AppCompatActivity() {

    companion object {
        lateinit var binding: CoordinatorLayoutBinding

        var listTitles = arrayListOf("Shopping List", "Chores", "Android Tutorials")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = CoordinatorLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.listsRecyclerview.adapter = ListSelectionRecyclerViewAdapter(listTitles)

        binding.fab.setOnClickListener {
            view.getViewById(R.id.fragment).visibility = View.VISIBLE
        }
    }
    class FragmentActivity: Fragment() {

        override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            return inflater.inflate(R.layout.fragment_text, container, false )
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            view.findViewById<Button>(R.id.add).setOnClickListener{
                view.findViewById<EditText>(R.id.editText).text.takeIf {
                    it.isNotEmpty()
                }?.let {
                    listTitles.add(view.findViewById<EditText>(R.id.editText).text.toString())
                    binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size - 1)
                    view.visibility = View.GONE
                }
            }
        }
    }

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