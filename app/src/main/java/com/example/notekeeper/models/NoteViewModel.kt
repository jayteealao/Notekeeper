package com.example.notekeeper.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notekeeper.MainApplication
import com.example.notekeeper.data.Note
import com.example.notekeeper.data.NoteRepository
import com.example.notekeeper.data.Notedb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepo: NoteRepository) : ViewModel() {

    fun populateDb() = viewModelScope.launch {
        noteRepo.prepopulateDb()
    }

    val notes: LiveData<List<Note>> = noteRepo.getnotes().asLiveData()

    var selectedNoteId: Int? = null

}