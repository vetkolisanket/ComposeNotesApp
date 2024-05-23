package com.example.composenotesapp.ui.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenotesapp.asState
import com.example.composenotesapp.domain.use_case.NotesUseCases
import com.example.composenotesapp.ui.add_edit_note.UIEvent
import com.example.composenotesapp.ui.models.NoteUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _notesState = mutableStateOf(NotesState())
    val notesState = _notesState.asState()

    private var recentlyDeletedNote: NoteUIModel? = null

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            notesUseCases.getNotes().collect {
                _notesState.value = notesState.value.copy(notes = it)
            }
        }
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.ToggleOrderSectionVisibility -> {
                _notesState.value =
                    notesState.value.copy(isOrderSectionVisible = !notesState.value.isOrderSectionVisible)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = event.note
                    notesUseCases.deleteNote(event.note.toNoteDomainModel())
                }
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

}