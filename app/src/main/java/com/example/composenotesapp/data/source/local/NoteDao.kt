package com.example.composenotesapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.composenotesapp.data.model.NoteDataModel

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(note: NoteDataModel)

}