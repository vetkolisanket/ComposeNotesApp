package com.example.composenotesapp.ui.add_edit_note

sealed class UIEvent {
    data object SaveNote : UIEvent()
    data class ShowSnackbar(val message: String) : UIEvent()
}