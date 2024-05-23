package com.example.composenotesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDataModel(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val time: Long,
    val color: Int
)