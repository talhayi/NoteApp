package com.example.noteapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.data.model.Note

interface NoteRepositoryInterface {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note:Note)

    fun getNote(): LiveData<List<Note>>
}