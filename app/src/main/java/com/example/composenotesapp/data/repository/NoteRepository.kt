package com.example.composenotesapp.data.repository

import com.example.composenotesapp.IDispatcherProvider
import com.example.composenotesapp.data.source.local.NoteDao
import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.toNoteDomainModelList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val dao: NoteDao,
    val dispatchers: IDispatcherProvider
) : INoteRepository {
    override suspend fun insertNote(note: NoteDomainModel) {
        withContext(dispatchers.io) {
            dao.insertNote(note.toNoteDataModel())
        }
    }

    override suspend fun getNotes(): Flow<List<NoteDomainModel>> {
        return withContext(dispatchers.io) {
            dao.getNotes().map {
                it.toNoteDomainModelList()
            }
        }
    }

    override suspend fun deleteNote(note: NoteDomainModel) {
        withContext(dispatchers.io) {
            dao.deleteNote(note.toNoteDataModel())
        }
    }
}