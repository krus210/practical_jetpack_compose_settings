package com.practicaljetpackcompose.settings.ui.state

import com.practicaljetpackcompose.settings.ui.theme.Theme

data class SettingsState(
    val notificationEnabled: Boolean = false,
    val hintsEnabled: Boolean = false,
    val marketingOption: MarketingOption = MarketingOption.ALLOWED,
    val themeOption: Theme = Theme.SYSTEM
)
