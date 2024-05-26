package com.example.composenotesapp.ui.notes

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelectable
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.composenotesapp.R
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

    @Test
    fun clickToggleOrderSectionToVisibleClickAgain_isHidden() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isNotDisplayed()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isDisplayed()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isNotDisplayed()
    }

    @Test
    fun clickTitleRadioButton_toggleSelection() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isNotDisplayed()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription(applicationContext.getString(R.string.date))
            .assertIsSelectable()
        composeRule.onNodeWithContentDescription(applicationContext.getString(R.string.title))
            .assertIsNotSelected()
        composeRule.onNodeWithContentDescription(applicationContext.getString(R.string.title))
            .performClick()
        composeRule.onNodeWithContentDescription(applicationContext.getString(R.string.date))
            .assertIsNotSelected()
        composeRule.onNodeWithContentDescription(applicationContext.getString(R.string.title))
            .assertIsSelected()
    }

}