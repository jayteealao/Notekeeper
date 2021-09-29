package com.example.notekeeper

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.adapters.NoteListAdapter
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.databinding.FragmentNoteListBinding
import com.example.notekeeper.models.NoteViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteListFragment : Fragment() {

    private val notesmodel: NoteViewModel by activityViewModels()

    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        Log.d("devdebugListFragment", "on create view")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("devdebug", "onviewcreated")

        val adapter = NoteListAdapter()
        Log.d("devdebug", "adapter initialised")

        binding.notesList.adapter = adapter
        binding.notesList.layoutManager = LinearLayoutManager(context)
        notesmodel.notes.observe(viewLifecycleOwner) { notes ->
//            Log.d("ListFragment", "sample note ${notes[0]}")

            adapter.submitList(notes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}