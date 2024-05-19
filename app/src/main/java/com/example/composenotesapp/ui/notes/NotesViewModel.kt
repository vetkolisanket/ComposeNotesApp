package com.example.composenotesapp.ui.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composenotesapp.asState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor() : ViewModel() {

    private val _notesState = mutableStateOf(NotesState())
    val notesState = _notesState.asState()

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.ToggleOrderSectionVisibility -> {
                _notesState.value =
                    notesState.value.copy(isOrderSectionVisible = !notesState.value.isOrderSectionVisible)
            }
        }
    }

}