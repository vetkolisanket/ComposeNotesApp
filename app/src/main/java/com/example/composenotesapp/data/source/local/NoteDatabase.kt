package com.example.composenotesapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composenotesapp.data.model.NoteDataModel

@Database(entities = [NoteDataModel::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}