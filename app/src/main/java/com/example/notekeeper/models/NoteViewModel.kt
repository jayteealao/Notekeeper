package com.example.notekeeper.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.data.NoteInfo

class NoteViewModel(repository: DataManager) : ViewModel() {

    private val notes: MutableLiveData<ArrayList<NoteInfo>> by lazy {
        MutableLiveData<ArrayList<NoteInfo>>().also {
            loadData()
        }
    }

    fun loadData() = DataManager.notes
}