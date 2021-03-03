package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.FragmentTextBinding

//import ge.tsu.android.myapplication.databinding.FragmentTextBinding

class FragmentActivity: Fragment() {
//

     lateinit var binding: FragmentTextBinding
//
//    public lateinit var textView: TextView
//    lateinit var button: Button
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(R.layout.fragment_text, container, false )
//        binding.editText
        return view
    }
}
interface OnDogSelected {
    fun onDogSelected(fargmentActivity: ge.tsu.android.myapplication.recycle.FragmentActivity)
}