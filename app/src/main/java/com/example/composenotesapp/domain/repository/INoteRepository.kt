package com.example.composenotesapp.domain.repository

import com.example.composenotesapp.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    suspend fun insertNote(note: NoteDomainModel)
    suspend fun getNotes(): Flow<List<NoteDomainModel>>
    suspend fun deleteNote(note: NoteDomainModel)
    suspend fun getNote(noteId: Int): Flow<NoteDomainModel?>

}