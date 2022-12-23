package com.practicaljetpackcompose.settings.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.practicaljetpackcompose.settings.ui.state.MarketingOption
import com.practicaljetpackcompose.settings.ui.state.SettingsState
import com.practicaljetpackcompose.settings.ui.theme.Theme
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel: ViewModel() {

    val uiState = MutableStateFlow(value = SettingsState())

    fun toggleNotificationSettings() {
        uiState.value = uiState.value.copy(
            notificationEnabled = !uiState.value.notificationEnabled
        )
    }

    fun toggleHintsSettings() {
        uiState.value = uiState.value.copy(
            hintsEnabled = !uiState.value.hintsEnabled
        )
    }

    fun setMarketingSettings(option: MarketingOption) {
        uiState.value = uiState.value.copy(
            marketingOption = option
        )
    }

    fun setTheme(theme: Theme) {
        uiState.value = uiState.value.copy(
            themeOption = theme
        )
    }
}