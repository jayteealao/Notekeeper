package com.example.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notekeeper.databinding.FragmentNoteEditBinding
import com.example.notekeeper.models.NoteItemModel
import com.example.notekeeper.models.NoteViewModel
import android.text.Editable.Factory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NoteEditFragment : Fragment() {

    private val notesmodel: NoteViewModel by activityViewModels()
    private val noteItemModel: NoteItemModel by viewModels()
    private val arg: NoteEditFragmentArgs by navArgs()
    private val editableFactory = Factory()

    private var _binding: FragmentNoteEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNoteEditBinding.inflate(inflater, container, false)
        if (arg.id == 0) {
            noteItemModel.newNote(0)
        }
        else {
            noteItemModel.setNote(arg.id)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteItemModel.selectedNote?.observe(viewLifecycleOwner) { note ->
            binding.editNoteTitle.text =
                editableFactory.newEditable(note.noteTitle ?: "test")
            binding.editNoteText.text =
                editableFactory.newEditable(note.noteText ?: "test")
        }
    }

    override fun onStop() {
        super.onStop()
        if (arg.id == 0) {
            if (binding.editNoteTitle.text.isNotEmpty() or binding.editNoteText.text.isNotEmpty()) {

                noteItemModel.saveNote(
                    binding.editNoteTitle.text.toString(),
                    binding.editNoteText.text.toString()
                )
            }
        }
        else {
            noteItemModel.updateNote(
                binding.editNoteTitle.text.toString(),
                binding.editNoteText.text.toString()
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}