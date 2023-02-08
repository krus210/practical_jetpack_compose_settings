package com.practicaljetpackcompose.settings

import androidx.annotation.StringRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.practicaljetpackcompose.settings.ui.Settings
import com.practicaljetpackcompose.settings.ui.Tags
import org.junit.Rule
import org.junit.Test

class SettingsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Enable_Notification_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_enable_notifications)
    }

    @Test
    fun Show_Hints_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_show_hints)
    }

    @Test
    fun View_Subscription_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_manage_subscription)
    }

    @Test
    fun App_Version_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_app_version_title)
    }

    @Test
    fun Theme_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_option_theme)
    }

    @Test
    fun Marketing_Options_Setting_Is_Displayed() {
        assertSettingsIsDisplayed(R.string.settings_option_theme)
    }

    @Test
    fun Enable_Notifications_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.settings_enable_notifications)
        ).performClick()
        composeTestRule.onNodeWithTag(
            Tags.TAG_TOGGLE_ITEM
        ).assertIsOn()
    }

    @Test
    fun Show_Hints_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.settings_show_hints)
        ).performClick()
        composeTestRule.onNodeWithTag(
            Tags.TAG_CHECK_ITEM
        ).assertIsOn()
    }

    @Test
    fun Marketing_Options_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.resources.getStringArray(R.array.setting_options_marketing_choice)[1]
        ).performClick()
        composeTestRule.onNodeWithTag(
            Tags.TAG_MARKETING_OPTION + 1
        ).assertIsSelected()
    }

    private fun assertSettingsIsDisplayed(
        @StringRes title: Int
    ) {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(title)
        ).assertIsDisplayed()
    }
}