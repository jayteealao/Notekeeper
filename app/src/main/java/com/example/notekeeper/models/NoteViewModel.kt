package com.example.notekeeper.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.data.NoteDao
import com.example.notekeeper.data.NoteInfo

class NoteViewModel(repository: DataManager) : ViewModel() {

    private val notes: MutableLiveData<List<NoteInfo>> = noteDao.getAll()

    fun loadData() = DataManager.notes
}