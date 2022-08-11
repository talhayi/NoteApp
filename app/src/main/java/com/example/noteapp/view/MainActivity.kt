package com.example.noteapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.adapter.NoteClickDeleteInterface
import com.example.noteapp.adapter.NoteClickInterface
import com.example.noteapp.adapter.NoteRVAdapter
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var binding: ActivityMainBinding

    private var noteList = ArrayList<Note>()

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(noteList,this,this)

        binding.notesRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.noteList.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        }
        binding.addNoteFAB.setOnClickListener {
            val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.title)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this,"${note.title} Deleted",Toast.LENGTH_LONG).show()
    }
}