package com.example.composenotesapp.domain.use_case

import com.example.composenotesapp.data.repository.FakeNoteRepository
import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.domain.util.NoteOrder
import com.example.composenotesapp.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<NoteDomainModel>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                NoteDomainModel(
                    title = c.toString(),
                    content = c.toString(),
                    time = index.toLong(),
                    color = index
                )
            )
        }

        notesToInsert.shuffle()
        notesToInsert.forEach { note ->
            runTest {
                fakeNoteRepository.insertNote(note)
            }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].title < notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].title > notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by date ascending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].time < notes[i+1].time)
        }
    }

    @Test
    fun `Order notes by date descending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].time > notes[i+1].time)
        }
    }

    @Test
    fun `Order notes by color ascending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].color < notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`() = runTest {
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assert(notes[i].color > notes[i+1].color)
        }
    }
}