package com.example.composenotesapp.ui.notes

import com.example.composenotesapp.ui.models.NoteUIModel

data class NotesState(
    val isOrderSectionVisible: Boolean = false,
    val notes: List<NoteUIModel> = emptyList()
)