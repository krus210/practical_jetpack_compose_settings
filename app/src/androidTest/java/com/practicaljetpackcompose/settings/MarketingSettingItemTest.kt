package com.practicaljetpackcompose.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.practicaljetpackcompose.settings.ui.MarketingSettingItem
import com.practicaljetpackcompose.settings.ui.Tags
import com.practicaljetpackcompose.settings.ui.state.MarketingOption
import org.junit.Rule
import org.junit.Test

class MarketingSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Marketing_Option_Selected() {
        val option = MarketingOption.NOT_ALLOWED
        composeTestRule.setContent {
            MarketingSettingItem(
                selectedOption = option,
                onOptionSelected = {}
            )
        }
        composeTestRule
            .onNodeWithTag(Tags.TAG_MARKETING_OPTION + option.id)
            .assertIsSelected()
    }
}