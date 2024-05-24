package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.domain.util.NoteOrder
import com.example.composenotesapp.domain.util.OrderType
import com.example.composenotesapp.toNoteUIModelList
import com.example.composenotesapp.ui.models.NoteUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(val repository: INoteRepository) {

    suspend operator fun invoke(noteOrder: NoteOrder): Flow<List<NoteUIModel>> {
        return repository.getNotes().map { notesDomainModelList ->
            val notes = notesDomainModelList.toNoteUIModelList()
            when (noteOrder.orderType) {
                OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedBy { note -> note.color }
                        is NoteOrder.Date -> notes.sortedBy { note -> note.time }
                        is NoteOrder.Title -> notes.sortedBy { note -> note.title }
                    }
                }

                OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedByDescending { note -> note.color }
                        is NoteOrder.Date -> notes.sortedByDescending { note -> note.time }
                        is NoteOrder.Title -> notes.sortedByDescending { note -> note.title }
                    }
                }
            }
        }
    }

}
