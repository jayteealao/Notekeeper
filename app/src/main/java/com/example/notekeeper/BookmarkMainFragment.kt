package com.example.notekeeper

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.notekeeper.databinding.FragmentBookmarkMainBinding
import com.example.notekeeper.utils.onClickDebounced

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookmarkMainFragment : Fragment() {

    private var _binding: FragmentBookmarkMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookmarkMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = InputType.TYPE_TEXT_VARIATION_URI

        binding.buttonSecond.onClickDebounced {
            MaterialDialog(requireContext()).show {
                input(inputType = type) { dialog, text ->
                    // Text submitted with the action button
                }
                positiveButton(R.string.add_bookmark)
            }
        }
//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}