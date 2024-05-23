package com.example.composenotesapp.ui.notes

import com.example.composenotesapp.ui.models.NoteUIModel

sealed class NotesEvent {
    data class DeleteNote(val note: NoteUIModel) : NotesEvent()
    data object ToggleOrderSectionVisibility : NotesEvent()
    data object RestoreNote : NotesEvent()
}