package com.example.composenotesapp.ui.add_edit_note

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.composenotesapp.asState
import com.example.composenotesapp.ui.models.NotesUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor() : ViewModel() {

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

    private val _noteColorState = mutableIntStateOf(NotesUIModel.colors.random().toArgb())
    val noteColorState = _noteColorState.asState()

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

            }
        }
    }

}