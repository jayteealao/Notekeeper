package com.example.notekeeper.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.R
import com.example.notekeeper.data.NoteInfo

class NoteListAdapter(private val notes: ArrayList<NoteInfo>) : RecyclerView.Adapter<NoteListAdapter.NoteItemViewHolder>() {

    class NoteItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val noteTitle : TextView
        val noteText : TextView

        init {
            noteTitle = view.findViewById(R.id.textCourse)
            noteText = view.findViewById(R.id.textTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_list, parent, false)

        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note : NoteInfo = notes[position]

        holder.noteTitle.text = note.title
        holder.noteText.text = note.text
    }

    override fun getItemCount() = notes.size
}