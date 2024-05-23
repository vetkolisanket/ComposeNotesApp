package com.example.composenotesapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composenotesapp.data.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(note: NoteDataModel)

    @Query("SELECT * FROM notedatamodel")
    abstract fun getNotes(): Flow<List<NoteDataModel>>

    @Delete
    abstract fun deleteNote(note: NoteDataModel)

    @Query("SELECT * FROM notedatamodel WHERE id = :noteId")
    abstract fun getNote(noteId: Int): Flow<NoteDataModel>

}