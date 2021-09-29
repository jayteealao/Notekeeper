package com.example.notekeeper.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.notekeeper.MainApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class NoteRepository (){

    private val db = Notedb.getInstance(MainApplication.applicationContext())

    fun getnotes() = db.noteDao().getAll()

    fun getNote(id: Int) = db.noteDao().getNotebyId(id)

    fun newNote(id: Int): Flow<Note> {
        val note: Flow<Note> = flow {
            emit(
                Note(
                    id,
                    "",
                    "",
                    Date().time
                )
            )
        }
        return note
    }

    suspend fun saveNote(note: Note) = db.noteDao().InsertNote(note)

    suspend fun updateNote(note: Note) = db.noteDao().updateNote(note)

    companion object {
    }
    
    suspend fun prepopulateDb(db: Notedb) {
        db.noteDao().InsertNote(
            Note(
                0,
                "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime",
                Date().time
            ),
            Note(
                0,
                "Delegating intents",
                "PendingIntents are powerful; they delegate much more than just a component invocation",
                Date().time
            ),
            Note(
                0,
                "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?",
                Date().time
            ),
            Note(
                0,
                "Parameters",
                "Leverage variable-length parameter lists",
                Date().time
            ),
            Note(
                0,
                "Anonymous classes",
                "Anonymous classes simplify implementing one-use types",
                Date().time
            )
        )


    }

}