package com.example.notekeeper.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notekeeper.MainApplication
import com.example.notekeeper.data.Note
import com.example.notekeeper.data.NoteRepository
import com.example.notekeeper.data.Notedb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {

    private val repo = NoteRepository()

    init {
        if (repo.getnotes().asLiveData()?.value?.size == 0) {
            viewModelScope.launch(Dispatchers.IO) {
                NoteRepository().prepopulateDb(Notedb.getInstance(MainApplication.applicationContext()))
            }
        }
    }

    val notes: LiveData<List<Note>> = repo.getnotes().asLiveData()

    var selectedNoteId: Int? = null

}