package com.example.composenotesapp.di

import com.example.composenotesapp.data.repository.NoteRepository
import com.example.composenotesapp.domain.repository.INoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(repository: NoteRepository): INoteRepository

}