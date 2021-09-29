package com.example.notekeeper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertNote(vararg notes: Note)

    @Query("SELECT * FROM note WHERE id == :id")
    fun getNotebyId(id: Int): Flow<Note>

    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

}
