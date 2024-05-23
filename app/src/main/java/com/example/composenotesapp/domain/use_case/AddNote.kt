package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.InvalidNoteException
import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.ui.models.NoteUIModel
import javax.inject.Inject

class AddNote @Inject constructor(private val repository: INoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: NoteUIModel) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty!")
        }
        repository.insertNote(note.toNoteDomainModel())
    }

}