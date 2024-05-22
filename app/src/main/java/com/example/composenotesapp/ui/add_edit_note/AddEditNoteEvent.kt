package com.example.composenotesapp.ui.add_edit_note

import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

sealed class AddEditNoteEvent {
    data class ChangedTitle(val text: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangedContent(val text: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    data object SaveNote: AddEditNoteEvent()

}