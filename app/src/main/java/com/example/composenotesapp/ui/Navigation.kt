package com.example.composenotesapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composenotesapp.ui.add_edit_note.AddEditNoteScreen
import com.example.composenotesapp.ui.notes.NotesScreen

sealed class Route(val name: String) {
    data object Notes : Route("notes")
    data object AddEditNote : Route("add-edit-note")
}

@Composable
fun NotesNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.Notes.name) {
        composable(Route.Notes.name) {
            NotesScreen(navController)
        }
        composable(
            route = Route.AddEditNote.name + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController = navController, noteColor = color)
        }
    }
}
