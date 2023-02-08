package com.practicaljetpackcompose.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.practicaljetpackcompose.settings.ui.Tags
import com.practicaljetpackcompose.settings.ui.ThemeSettingItem
import com.practicaljetpackcompose.settings.ui.theme.Theme
import org.junit.Rule
import org.junit.Test

class ThemeSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Selected_Theme_Dispalyed() {
        val option = Theme.DARK

        composeTestRule.setContent {
            ThemeSettingItem(
                selectedTheme = option,
                onOptionSelected = {}
            )
        }
        composeTestRule
            .onNodeWithTag(Tags.TAG_THEME, useUnmergedTree = true)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(option.label)
            )
    }

    @Test
    fun Theme_Options_Displayed() {
        composeTestRule.setContent {
            ThemeSettingItem(
                selectedTheme = Theme.DARK,
                onOptionSelected = {}
            )
        }
        composeTestRule
            .onNodeWithTag(Tags.TAG_SELECT_THEME)
            .performClick()

        Theme.values().forEach { theme ->
            composeTestRule
                .onNodeWithTag(
                    Tags.TAG_THEME_OPTION + theme.label
                ).assertIsDisplayed()
        }
    }
}