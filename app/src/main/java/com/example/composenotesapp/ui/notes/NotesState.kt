package com.example.composenotesapp.ui.notes

import com.example.composenotesapp.domain.util.NoteOrder
import com.example.composenotesapp.domain.util.OrderType
import com.example.composenotesapp.ui.models.NoteUIModel

data class NotesState(
    val isOrderSectionVisible: Boolean = false,
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val notes: List<NoteUIModel> = emptyList()
)