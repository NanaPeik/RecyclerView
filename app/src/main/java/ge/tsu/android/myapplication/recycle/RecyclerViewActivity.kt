package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.CoordinatorLayoutBinding
import ge.tsu.android.myapplication.databinding.FragmentTextBinding
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity(), OnDogSelected {
//    lateinit var listsRecyclerView: RecyclerView

    private lateinit var binding: CoordinatorLayoutBinding

    var listTitles = arrayListOf("Shopping List", "Chores", "Android Tutorials")
    var finalString: String=""
    var fragmentActivity = ge.tsu.android.myapplication.recycle.FragmentActivity()

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= CoordinatorLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.listsRecyclerview.layoutManager=LinearLayoutManager(this)
        binding.listsRecyclerview.adapter= ListSelectionRecyclerViewAdapter(listTitles)
        binding.fab.setOnClickListener{
//            view.getViewById(R.id.fragment).visibility=View.VISIBLE
//            binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)
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
////        binding.listsRecyclerview.adapter?.notifyDataSetChanged()
//        binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)
            onDogSelected(fragmentActivity)
        }
    }



    override fun onDogSelected(fargmentActivity: ge.tsu.android.myapplication.recycle.FragmentActivity) {
        listTitles.add(fragmentActivity.binding.editText.text.toString())
        binding.listsRecyclerview.adapter?.notifyItemInserted(listTitles.size-1)    }


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

//    class FragmentActivity: Fragment() {
//
//        lateinit var textView: TextView
//        lateinit var button: Button
//
//        override fun onCreateView(inflater: LayoutInflater,
//                                  container: ViewGroup?,
//                                  savedInstanceState: Bundle?): View? {
//
//            val view: View = inflater.inflate(R.layout.fragment_text, container, false )
//            textView=view.findViewById(R.id.itemString)
//            button=view.findViewById(R.id.fab)
//            listTitles.add(textView.text.toString())
//            view.visibility=View.GONE
//            return view
//        }
//
//    }
}