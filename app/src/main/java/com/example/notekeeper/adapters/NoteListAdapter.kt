package com.example.notekeeper.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.NoteEditFragmentDirections
import com.example.notekeeper.NoteListFragmentDirections
import com.example.notekeeper.data.Note
import com.example.notekeeper.databinding.ItemNoteListBinding

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteItemViewHolder>(NoteDiffCallback()) {

    class NoteItemViewHolder(
        val binding: ItemNoteListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val noteTitle : TextView
        val noteText : TextView

        init {
            noteTitle = binding.textCourse
            noteText = binding.textTitle
        }

        fun navigateToNote(
            note: Note,
            view: View
        ) {
            val direction = NoteListFragmentDirections.actionNoteListFragmentToNoteEditFragment(note.id)
            view.findNavController().navigate(direction)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        Log.d("devdebug", "viewholder created")

        return NoteItemViewHolder(ItemNoteListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note : Note = getItem(position)

        Log.d("devdebug", "viewholder $position")

        with(holder) {
            binding.textCourse.text = note.noteTitle
            binding.textTitle.text = note.noteText
            binding.root.setOnClickListener { view ->
                holder.navigateToNote(note, binding.root)
            }

        }

    }

}

private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}