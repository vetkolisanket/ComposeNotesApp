package com.example.composenotesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composenotesapp.domain.model.NoteDomainModel

@Entity
data class NoteDataModel(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val time: Long,
    val color: Int
) {
    fun toNoteDomainModel(): NoteDomainModel {
        return NoteDomainModel(id, title, content, time, color)
    }
}