package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title :String,
    val titleDescription:String,
    val time : String
    ){
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
}
