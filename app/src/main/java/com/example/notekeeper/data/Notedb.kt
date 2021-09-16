package com.example.notekeeper.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val noteTitle: String?,
    val noteText: String,
    val createdAt: Long,
    val updatedAt: Long? = null
)

@Dao
interface NoteDao {
    @Insert
    fun InsertNote(vararg notes: Note)

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>
}

@Database(entities = arrayOf(Note::class), version = 1)
abstract class Notedb  : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile private var instance: Notedb? = null

        fun getInstance(context: Context): Notedb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) : Notedb {
            return Room.databaseBuilder(context, Notedb::class.java, "Notedbv1")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val database = getInstance(context)
                            database.noteDao().InsertNote(
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
                ).build()
        }
    }
}