package com.example.composenotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.composenotesapp.data.source.local.NoteDatabase
import com.example.composenotesapp.domain.repository.INoteRepository
import com.example.composenotesapp.domain.use_case.AddNote
import com.example.composenotesapp.domain.use_case.DeleteNote
import com.example.composenotesapp.domain.use_case.GetNote
import com.example.composenotesapp.domain.use_case.GetNotes
import com.example.composenotesapp.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: NoteDatabase) = database.dao

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: INoteRepository) = NotesUseCases(
        AddNote(repository),
        GetNotes(repository),
        DeleteNote(repository),
        GetNote(repository)
    )

}