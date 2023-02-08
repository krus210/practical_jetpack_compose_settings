package com.practicaljetpackcompose.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.practicaljetpackcompose.settings.ui.NotificationsSettings
import com.practicaljetpackcompose.settings.ui.Tags
import org.junit.Rule
import org.junit.Test

class NotificationSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Enable Notifications"
        composeTestRule.setContent {
            NotificationsSettings(
                title = title,
                checked = true,
                onCheckedChanged = {}
            )
        }
        composeTestRule
            .onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Setting_Checked() {
        composeTestRule.setContent {
            NotificationsSettings(
                title = "Enable Notifications",
                checked = true,
                onCheckedChanged = {}
            )
        }
        composeTestRule
            .onNodeWithTag(Tags.TAG_TOGGLE_ITEM)
            .assertIsOn()
    }
}