package com.example.noteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "noteDescription")
    val noteDescription: String,

    @ColumnInfo(name = "time")
    val time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
