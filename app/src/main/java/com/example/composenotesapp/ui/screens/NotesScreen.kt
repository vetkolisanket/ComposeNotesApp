package com.example.composenotesapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenotesapp.R
import com.example.composenotesapp.ui.notes.NotesEvent
import com.example.composenotesapp.ui.notes.NotesViewModel
import com.example.composenotesapp.ui.notes.components.OrderSection

@Preview
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.notesState.value

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.notes_app),
                    style = MaterialTheme.typography.headlineLarge,
                )
                IconButton(
                    onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSectionVisibility) }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection()
            }
        }
    }
}