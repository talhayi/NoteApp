package com.example.noteapp.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.R
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.ActivityEditNoteBinding
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class EditNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditNoteBinding
    lateinit var viewModel: NoteViewModel
    var noteID = -1

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {

            var notTitle = intent.getStringExtra("noteTitle").toString()
            val notDescription = intent.getStringExtra("noteDescription").toString()
            noteID = intent.getIntExtra("noteID", 0)
            binding.saveUpdateBtn.text = "Update Note"
            binding.editNoteTitleET.setText(notTitle)
            binding.editNoteDescriptionET.setText(notDescription)

        } else {
            binding.saveUpdateBtn.text = "Save Note"
        }

        binding.saveUpdateBtn.setOnClickListener {
            val noteTitle = binding.editNoteTitleET.text.toString()
            val noteDescription = binding.editNoteDescriptionET.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentData: String = simpleDateFormat.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentData)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Update", Toast.LENGTH_LONG).show()
                }

            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentData: String = simpleDateFormat.format(Date())
                    val insertNote = Note(noteTitle, noteDescription, currentData)
                    viewModel.insertNote(insertNote)
                    Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show()
                    }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}