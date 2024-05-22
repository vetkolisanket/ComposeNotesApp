package com.example.composenotesapp.ui.add_edit_note

data class AddEditNoteTextState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
