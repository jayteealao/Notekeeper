package com.example.notekeeper.di

import android.content.Context
import com.example.notekeeper.data.Notedb
import com.example.notekeeper.data.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseDI {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Notedb {
        return Notedb.getInstance(context)
    }

    @Provides
    fun provideNoteDao(appDatabase: Notedb): NoteDao {
        return appDatabase.noteDao()
    }

}