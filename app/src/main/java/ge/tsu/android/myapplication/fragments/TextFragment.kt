package ge.tsu.android.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import ge.tsu.android.myapplication.databinding.FragmentTextBinding
import ge.tsu.android.myapplication.keys.ExtraKeys

class TextFragment : Fragment() {
    private lateinit var binding: FragmentTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextBinding.inflate(inflater, container, false)
        binding.removeFragment.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStackImmediate()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            setFragmentResult(
                ExtraKeys.FRAGMENT_REQUEST_KEY,
                bundleOf(
                    ExtraKeys.FRAGMENT_RESULT_EXTRA to binding.editText.text.toString(),
                    ExtraKeys.FRAGMENT_RESULT_EXTRA_DETILES to binding.detiles.text.toString()
                )
            )
            parentFragmentManager.commit {
                remove(this@TextFragment)
            }
        }

        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
