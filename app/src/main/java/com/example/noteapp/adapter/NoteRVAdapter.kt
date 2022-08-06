package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.NoteRvItemBinding

class NoteRVAdapter(
    private val noteList: ArrayList<Note>,
    private val noteClickInterface: NoteClickInterface,
    private val noteClickDeleteInterface: NoteClickDeleteInterface
): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: NoteRvItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =NoteRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.binding.noteTitleTV.text = noteList[position].title
        holder.binding.timeTV.text = "Last Updated: " + noteList[position].time

        holder.binding.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(noteList[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(noteList[position])
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateList(newList: List<Note>){
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note:Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}