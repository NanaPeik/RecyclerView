package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import ge.tsu.android.myapplication.R

class ExampleFragment : Fragment(R.layout.fragment_text){
    lateinit var viewModel: ItemViewModel
    lateinit var editText : EditText
    lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
//        viewModel.selectedItem.value=view?.findViewById<EditText>(R.id.editText)?.text.toString()
        if (view != null) {
            editText=view.findViewById(R.id.editText)
            button=view.findViewById(R.id.add)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        button.setOnClickListener{
            viewModel.currentString.value=editText.text.toString()
        }

//        viewModel.currentString.observe(viewLifecycleOwner, Observer {
//            if (view != null) {
//                view.findViewById<EditText>(R.id.editText).setInputExtras(it.toString())
//            }
//        })
    }
//    fun onItemClicked(item: String) {
//        viewModel.selectItem(item)
//    }
}
