package com.example.composenotesapp.ui.add_edit_note

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenotesapp.InvalidNoteException
import com.example.composenotesapp.asState
import com.example.composenotesapp.domain.use_case.NotesUseCases
import com.example.composenotesapp.ui.models.NoteUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val notesUseCases: NotesUseCases,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitleState = mutableStateOf(
        AddEditNoteTextState(
            hint = "Enter title..."
        )
    )
    val noteTitleState = _noteTitleState.asState()

    private val _noteContentState = mutableStateOf(
        AddEditNoteTextState(
            hint = "Enter some content"
        )
    )
    val noteContentState = _noteContentState.asState()

    private val _noteColorState = mutableIntStateOf(NoteUIModel.colors.random().toArgb())
    val noteColorState = _noteColorState.asState()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    notesUseCases.getNote(noteId).collectLatest { note ->
                        currentNoteId = note.id
                        _noteTitleState.value = noteTitleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContentState.value = noteContentState.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColorState.intValue = event.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    isHintVisible = noteContentState.value.text.isBlank()
                            && !event.focusState.isFocused
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    isHintVisible = noteTitleState.value.text.isBlank()
                            && !event.focusState.isFocused
                )
            }

            is AddEditNoteEvent.ChangedContent -> {
                _noteContentState.value = noteContentState.value.copy(
                    text = event.text
                )
            }

            is AddEditNoteEvent.ChangedTitle -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    text = event.text
                )
            }

            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCases.addNote(
                            NoteUIModel(
                                title = noteTitleState.value.text,
                                content = noteContentState.value.text,
                                time = System.currentTimeMillis(),
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UIEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(UIEvent.ShowSnackbar("There was some error while saving note!"))
                    }
                }
            }
        }
    }

}