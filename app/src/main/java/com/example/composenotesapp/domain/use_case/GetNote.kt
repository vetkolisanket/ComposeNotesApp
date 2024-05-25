package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.ui.models.NoteUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNote @Inject constructor(val repository: INoteRepository) {

    suspend operator fun invoke(noteId: Int): Flow<NoteUIModel> {
        return repository.getNote(noteId).map {
            it!!.toNoteUIModel() //todo handle note coming null properly
        }
    }

}