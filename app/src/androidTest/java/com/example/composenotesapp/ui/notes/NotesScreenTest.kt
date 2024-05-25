package com.example.composenotesapp.ui.notes

import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenotesapp.TestTags
import com.example.composenotesapp.di.AppModule
import com.example.composenotesapp.ui.MainActivity
import com.example.composenotesapp.ui.Route
import com.example.composenotesapp.ui.theme.ComposeNotesAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            ComposeNotesAppTheme {
                NavHost(navController = navController, startDestination = Route.Notes.name) {
                    composable(Route.Notes.name) {
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isNotDisplayed()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isDisplayed()
    }

}