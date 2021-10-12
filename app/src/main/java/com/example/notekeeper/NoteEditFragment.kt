package com.example.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notekeeper.databinding.FragmentNoteEditBinding
import com.example.notekeeper.models.NoteItemModel
import com.example.notekeeper.models.NoteViewModel
import android.text.Editable.Factory
import android.view.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class NoteEditFragment : Fragment() {

    private val notesmodel: NoteViewModel by activityViewModels()
    private val noteItemModel: NoteItemModel by viewModels()
    private val arg: NoteEditFragmentArgs by navArgs()
    private val editableFactory = Factory()

    private var _binding: FragmentNoteEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                editableFactory.newEditable(note.noteText)
        }
    }



    override fun onStop() {
        super.onStop()
        if (arg.id == 0) {
            if (binding.editNoteTitle.text.isNotBlank() or binding.editNoteText.text.isNotBlank()) {

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val setting = menu.findItem(R.id.action_settings)
        setting.isVisible = false
        val delete = menu.findItem(R.id.edit_menu_delete)
        noteItemModel.selectedNote?.observe(viewLifecycleOwner) { note ->
            if (note.noteText.isEmpty() and note.noteTitle.isNullOrEmpty()) {
                delete.isEnabled = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_menu_delete -> handleDeleteAction()
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun handleDeleteAction(): Boolean {

        noteItemModel.deleteNote()
        val direction = NoteEditFragmentDirections.actionNoteEditFragmentToNoteListFragment()
        findNavController().navigate(direction)
        return true
    }
}