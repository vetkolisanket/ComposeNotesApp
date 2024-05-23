package com.example.composenotesapp.domain.repository

import com.example.composenotesapp.domain.model.NoteDomainModel

interface INoteRepository {

    suspend fun insertNote(note: NoteDomainModel)

}