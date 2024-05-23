package com.example.composenotesapp.domain.use_case

data class NotesUseCases(
    val addNote: AddNote,
    val getNotes: GetNotes,
    val deleteNote: DeleteNote
)