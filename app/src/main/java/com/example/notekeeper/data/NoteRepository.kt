package com.example.notekeeper.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao){

    fun getnotes() = noteDao.getAll()

    fun getNote(id: Int) = noteDao.getNotebyId(id)

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

    suspend fun saveNote(note: Note) = noteDao.InsertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    
    suspend fun prepopulateDb() {
        noteDao.InsertNote(
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

    suspend fun deleteNote(note: Note?) = noteDao.deleteNote(note)

}