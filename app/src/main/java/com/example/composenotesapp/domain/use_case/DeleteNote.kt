package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.ui.models.NoteUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteNote @Inject constructor(val repository: INoteRepository) {

    suspend operator fun invoke(note: NoteDomainModel) {
        return repository.deleteNote(note)
    }

}