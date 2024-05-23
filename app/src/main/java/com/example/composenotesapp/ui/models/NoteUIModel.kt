package com.example.composenotesapp.ui.models

import com.example.composenotesapp.domain.model.NoteDomainModel
import com.example.composenotesapp.ui.theme.BabyBlue
import com.example.composenotesapp.ui.theme.LightGreen
import com.example.composenotesapp.ui.theme.RedOrange
import com.example.composenotesapp.ui.theme.RedPink
import com.example.composenotesapp.ui.theme.Violet

data class NoteUIModel(
    val id: Int? = null,
    val title: String,
    val content: String,
    val time: Long,
    val color: Int
) {
    fun toNoteDomainModel(): NoteDomainModel {
        return NoteDomainModel(id, title, content, time, color)
    }

    companion object {
        val colors = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }

}