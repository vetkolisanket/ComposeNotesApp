package com.example.composenotesapp.ui.notes

sealed class NotesEvent {
    data object ToggleOrderSectionVisibility: NotesEvent()
}