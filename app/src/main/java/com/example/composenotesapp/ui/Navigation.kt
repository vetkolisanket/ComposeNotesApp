package com.example.composenotesapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenotesapp.ui.screens.NotesScreen

sealed class Route(val name: String) {
    data object Notes: Route("notes")
    data object AddEditNote: Route("add-edit-note")
}

@Composable
fun NotesNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.Notes.name) {
        composable(Route.Notes.name) {
            NotesScreen()
        }
    }
}
