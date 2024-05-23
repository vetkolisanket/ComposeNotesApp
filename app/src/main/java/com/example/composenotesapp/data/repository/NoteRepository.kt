package com.example.composenotesapp.data.repository

import com.example.composenotesapp.data.source.local.NoteDao
import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.repository.INoteRepository
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) : INoteRepository {
    override suspend fun insertNote(note: NoteDomainModel) {
        dao.insertNote(note.toNoteDataModel())
    }
}