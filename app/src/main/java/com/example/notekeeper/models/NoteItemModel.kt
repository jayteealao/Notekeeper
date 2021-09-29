package com.example.notekeeper.models

import android.util.Log
import androidx.lifecycle.*
import com.example.notekeeper.data.Note
import com.example.notekeeper.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteItemModel: ViewModel() {

    private val repo = NoteRepository()
    var selectedNote: LiveData<Note>? = null


    var selectedNoteId: Int? = null

    var noteTitle: String? = null

    var noteText: String? = null

    fun setNote(noteId: Int) {
        Log.d("devdebug", "noteid passed to setNote: $noteId")
        selectedNoteId = noteId
        Log.d("devdebug", "note retrieved: $selectedNote")

        selectedNote = repo.getNote(noteId).asLiveData()

    }

    fun newNote(noteId: Int) {
        selectedNoteId = noteId
        selectedNote = repo.newNote(noteId).asLiveData()
    }

    fun saveNote(title: String, text: String) {
        var note: Note? = selectedNote?.value
        val newNote = Note(
            checkNotNull(note?.id),
            title,
            text,
            checkNotNull(note?.createdAt)
        )

        viewModelScope.launch(Dispatchers.IO) {
            repo.saveNote(newNote)
        }

    }

    fun updateNote(title: String, text: String) {
        var note: Note? = selectedNote?.value
        val updatedNote = Note(
            checkNotNull(note?.id),
            title,
            text,
            checkNotNull(note?.createdAt)
        )
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateNote(updatedNote)
        }
    }
}