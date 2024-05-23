package com.example.composenotesapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.example.composenotesapp.data.model.NoteDataModel
import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.ui.models.NoteUIModel

fun <T> MutableState<T>.asState(): State<T> {
    return this
}

fun List<NoteDataModel>.toNoteDomainModelList(): List<NoteDomainModel> {
    return map {
        NoteDomainModel(it.id, it.title, it.content, it.time, it.color)
    }
}

fun List<NoteDomainModel>.toNoteUIModelList(): List<NoteUIModel> {
    return map {
        NoteUIModel(it.id, it.title, it.content, it.time, it.color)
    }
}