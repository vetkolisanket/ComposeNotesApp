package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.InvalidNoteException
import com.example.composenotesapp.data.repository.FakeNoteRepository
import com.example.composenotesapp.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test
    fun `On valid note insert, should add into repository`() = runTest {
        val id = 1
        val noteToInsert = NoteDomainModel(id, "a", "b", 0, 9)

        var note = fakeNoteRepository.getNote(id).first()
        assertNull(note)
        addNote(noteToInsert.toNoteUIModel())
        note = fakeNoteRepository.getNote(id).first()
        assertEquals(noteToInsert, note)
    }

    @Test(expected = InvalidNoteException::class)
    fun `On note with empty title, should throw invalid note exception`() = runTest {
        val noteToInsert = NoteDomainModel(1, "", "b", 0, 9)

        addNote(noteToInsert.toNoteUIModel())
    }

    @Test(expected = InvalidNoteException::class)
    fun `On note with empty content, should throw invalid note exception`() = runTest {
        val noteToInsert = NoteDomainModel(1, "a", "", 0, 9)

        addNote(noteToInsert.toNoteUIModel())
    }
}