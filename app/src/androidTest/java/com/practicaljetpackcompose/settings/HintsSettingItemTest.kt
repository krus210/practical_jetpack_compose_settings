package com.practicaljetpackcompose.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.practicaljetpackcompose.settings.ui.HintSettingsItem
import com.practicaljetpackcompose.settings.ui.Tags
import org.junit.Rule
import org.junit.Test

class HintsSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Show hints"
        composeTestRule.setContent {
            HintSettingsItem(
                title = title,
                checked = true,
                onShowHintsToggled = {}
            )
        }
        composeTestRule
            .onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Setting_Checked() {
        composeTestRule.setContent {
            HintSettingsItem(
                title = "Show hints",
                checked = true,
                onShowHintsToggled = {}
            )
        }
        composeTestRule
            .onNodeWithTag(Tags.TAG_CHECK_ITEM)
            .assertIsOn()
    }
}