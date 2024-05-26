package com.example.composenotesapp.ui

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.composenotesapp.TestTags
import com.example.composenotesapp.di.AppModule
import com.example.composenotesapp.ui.theme.ComposeNotesAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            ComposeNotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }

    @Test
    fun testCreateNoteFlow() {
        val title = "test-title"
        val content = "test-content"

        composeRule.onNodeWithContentDescription("Add").performClick()

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE).performTextInput(title)
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_CONTENT).performTextInput(content)
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText(title).isDisplayed()
    }

    @Test
    fun testCreateAndEditNoteFlow() {
        val title = "test-title"
        val content = "test-content"
        val newTitle = "test-new-title"

        composeRule.onNodeWithContentDescription("Add").performClick()

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE).performTextInput(title)
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_CONTENT).performTextInput(content)
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText(title).performClick()

        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE).performTextClearance()
        composeRule.onNodeWithTag(TestTags.ADD_EDIT_TITLE).performTextInput(newTitle)
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText(newTitle).isDisplayed()
    }

}