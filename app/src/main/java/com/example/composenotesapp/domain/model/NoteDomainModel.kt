package com.example.composenotesapp.domain.model

import com.example.composenotesapp.data.model.NoteDataModel
import com.example.composenotesapp.ui.models.NoteUIModel

data class NoteDomainModel(
    val id: Int? = null,
    val title: String,
    val content: String,
    val time: Long,
    val color: Int
) {
    fun toNoteDataModel(): NoteDataModel {
        return NoteDataModel(id, title, content, time, color)
    }

    fun toNoteUIModel(): NoteUIModel {
        return NoteUIModel(id, title, content, time, color)
    }


}