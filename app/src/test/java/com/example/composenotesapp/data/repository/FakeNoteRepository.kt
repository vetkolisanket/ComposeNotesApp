package com.example.composenotesapp.data.repository

import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : INoteRepository {

    private val notes = mutableListOf<NoteDomainModel>()

    override suspend fun insertNote(note: NoteDomainModel) {
        notes.add(note)
    }

    override suspend fun getNotes(): Flow<List<NoteDomainModel>> {
        return flow {
            emit(notes)
        }
    }

    override suspend fun deleteNote(note: NoteDomainModel) {
        notes.remove(note)
    }

    override suspend fun getNote(noteId: Int): Flow<NoteDomainModel?> {
        return flow { emit(notes.find { it.id == noteId }) }
    }
}