package com.example.notekeeper.data

import androidx.room.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val noteTitle: String?,
    val noteText: String,
    val createdAt: Long,
    val updatedAt: Long? = null
)
