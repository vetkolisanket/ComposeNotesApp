package com.example.composenotesapp.data.source.local

import androidx.room.Dao
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

}