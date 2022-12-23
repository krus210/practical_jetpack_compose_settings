package com.practicaljetpackcompose.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicaljetpackcompose.settings.R
import com.practicaljetpackcompose.settings.ui.state.MarketingOption
import com.practicaljetpackcompose.settings.ui.state.SettingsState
import com.practicaljetpackcompose.settings.ui.theme.Theme
import com.practicaljetpackcompose.settings.ui.viewmodel.SettingsViewModel

@Composable
fun Settings() {
    val viewModel: SettingsViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            state = state,
            onCheckedChanged = viewModel::toggleNotificationSettings,
            onShowHintsToggled = viewModel::toggleHintsSettings,
            onManageSubscriptionClicked = {},
            onMarketingOptionSelected = viewModel::setMarketingSettings,
            onThemeOptionSelected = viewModel::setTheme
        )
    }
}

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    onCheckedChanged: () -> Unit,
    onShowHintsToggled: () -> Unit,
    onManageSubscriptionClicked: () -> Unit,
    onMarketingOptionSelected: (option: MarketingOption) -> Unit,
    onThemeOptionSelected: (option: Theme) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(start = 12.dp)
        ) {
            Icon(
                tint = MaterialTheme.colors.onSurface,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_go_back)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.title_settings),
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp
            )
        }
        Divider()
        NotificationsSettings(
            modifier = modifier.fillMaxWidth(),
            title = stringResource(id = R.string.settings_enable_notifications),
            checked = state.notificationEnabled,
            onCheckedChanged = onCheckedChanged
        )
        Divider()
        HintSettingsItem(
            modifier = modifier.fillMaxWidth(),
            title = stringResource(R.string.settings_show_hints),
            checked = state.hintsEnabled,
            onShowHintsToggled = onShowHintsToggled
        )
        Divider()
        ManageSubscriptionSettingItem(
            modifier = modifier.fillMaxWidth(),
            title = stringResource(R.string.settings_manage_subscription),
            onSettingClicked = onManageSubscriptionClicked
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        MarketingSettingItem(
            modifier = modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = onMarketingOptionSelected
        )
        Divider()
        ThemeSettingItem(
            modifier = modifier.fillMaxWidth(),
            selectedTheme = state.themeOption,
            onOptionSelected = onThemeOptionSelected
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        AppVersionSettingItem(
            modifier = modifier.fillMaxWidth(),
            appVersion = stringResource(R.string.settings_app_version)
        )
    }
}

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.heightIn(min = 56.dp)
    ) {
        content()
    }
}

@Composable
fun NotificationsSettings(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChanged: () -> Unit
) {
    SettingItem(modifier = modifier) {
        val notificationsEnabledState = if (checked) {
            R.string.cd_notifications_enabled
        } else {
            R.string.cd_notifications_disabled
        }.let {
            stringResource(id = it)
        }
        Row(
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = {
                        onCheckedChanged()
                    },
                    role = Role.Switch
                )
                .semantics {
                    stateDescription = notificationsEnabledState
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = null
            )
        }
    }
}

@Composable
fun HintSettingsItem(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onShowHintsToggled: () -> Unit
) {
    val hintsEnabledState = if (checked) {
        R.string.cd_hints_enabled
    } else {
        R.string.cd_hints_disabled
    }.let {
        stringResource(id = it)
    }
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = { onShowHintsToggled() },
                    role = Role.Checkbox
                )
                .semantics {
                    stateDescription = hintsEnabledState
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Checkbox(
                checked = checked,
                onCheckedChange = null
            )
        }
    }
}

@Composable
fun ManageSubscriptionSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    onSettingClicked: () -> Unit
) {
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable(
                    onClickLabel = stringResource(R.string.cd_open_subscription)
                ) { onSettingClicked() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SectionSpacer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12f))
    )
}

@Composable
fun MarketingSettingItem(
    modifier: Modifier = Modifier,
    selectedOption: MarketingOption,
    onOptionSelected: (option: MarketingOption) -> Unit
) {
    val options = stringArrayResource(R.array.setting_options_marketing_choice)
    SettingItem(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.settings_option_marketing))
            Spacer(modifier = Modifier.height(8.dp))
            options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = selectedOption.id == index,
                            onClick = {
                                onOptionSelected(
                                    MarketingOption
                                        .values()
                                        .first { it.id == index })
                            },
                            role = Role.RadioButton
                        )
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption.id == index,
                        onClick = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = option
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeSettingItem(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onOptionSelected: (option: Theme) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    SettingItem(modifier = modifier) {
        Row(
            modifier = modifier
                .clickable(
                    onClick = { expanded = !expanded },
                    onClickLabel = stringResource(R.string.cd_select_theme)
                )
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.settings_option_theme), modifier = Modifier.weight(1f))
            Text(text = stringResource(selectedTheme.label))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 16.dp, y = 0.dp)
        ) {
            Theme.values().forEach { theme ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(theme)
                        expanded = false
                    },
                    enabled = theme != selectedTheme
                ) {
                    Text(text = stringResource(theme.label))
                }
            }
        }
    }
}

@Composable
fun AppVersionSettingItem(
    modifier: Modifier = Modifier,
    appVersion: String
) {
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .semantics(mergeDescendants = true) {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.settings_app_version_title), modifier = Modifier.weight(1f))
            Text(text = appVersion)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsListPreview() {
    SettingsList(
        state = SettingsState(),
        onCheckedChanged = {},
        onShowHintsToggled = {},
        onManageSubscriptionClicked = {},
        onMarketingOptionSelected = {},
        onThemeOptionSelected = {}
    )
}