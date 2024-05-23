package com.example.composenotesapp.data.repository

import com.example.composenotesapp.data.model.NoteDataModel
import com.example.composenotesapp.data.source.local.NoteDao
import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.toNoteDomainModelList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) : INoteRepository {
    override suspend fun insertNote(note: NoteDomainModel) {
        dao.insertNote(note.toNoteDataModel())
    }

    override suspend fun getNotes(): Flow<List<NoteDomainModel>> {
        return dao.getNotes().map {
            it.toNoteDomainModelList()
        }
    }
}