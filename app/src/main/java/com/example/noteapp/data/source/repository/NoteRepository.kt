package com.example.noteapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.source.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
    ):NoteRepositoryInterface {


    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override fun getNote(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }


}