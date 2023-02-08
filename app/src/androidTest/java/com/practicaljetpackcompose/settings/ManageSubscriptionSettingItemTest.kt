package com.practicaljetpackcompose.settings

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.practicaljetpackcompose.settings.ui.HintSettingsItem
import com.practicaljetpackcompose.settings.ui.ManageSubscriptionSettingItem
import com.practicaljetpackcompose.settings.ui.Tags
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ManageSubscriptionSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Manage subscription"
        composeTestRule.setContent {
            ManageSubscriptionSettingItem(title = title) {}
        }
        composeTestRule
            .onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun On_Setting_Clicked_Triggered() {
        val title = "Manage subscription"
    val onSettingClicked: () -> Unit = mock()
        composeTestRule.setContent {
            ManageSubscriptionSettingItem(
                title = title,
                onSettingClicked = onSettingClicked
            )
        }
        composeTestRule
            .onNodeWithText(title)
            .performClick()
        verify(onSettingClicked).invoke()
    }
}