package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.toNoteUIModelList
import com.example.composenotesapp.ui.models.NoteUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(val repository: INoteRepository) {

    suspend operator fun invoke(): Flow<List<NoteUIModel>> {
        return repository.getNotes().map {
            it.toNoteUIModelList()
        }
    }

}
