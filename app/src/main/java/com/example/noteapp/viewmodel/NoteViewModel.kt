package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.source.NoteDatabase
import com.example.noteapp.data.source.repository.NoteRepository
import com.example.noteapp.data.source.repository.NoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
  //  val noteList: LiveData<List<Note>>
    val repository: NoteRepositoryInterface
): ViewModel() {


    var noteList = repository.getNote()
/*
    init {
        val dao = NoteDatabase.makeDatabase(application).noteDao()
        repository = NoteRepository(dao)
        noteList = repository.getNote()

    }

 */
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

}