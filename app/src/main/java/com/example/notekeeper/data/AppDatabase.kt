package com.example.notekeeper.data

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class Notedb  : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile private var instance: Notedb? = null


        fun getInstance(context: Context): Notedb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { it ->
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
                            Log.d("devdebugDATABASE", "Database created")
                        }
                    }
                ).build()
        }
    }
}