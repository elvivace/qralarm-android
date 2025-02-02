package com.sweak.qralarm.features.add_edit_alarm.advanced_settings

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweak.qralarm.R
import com.sweak.qralarm.core.designsystem.component.QRAlarmCard
import com.sweak.qralarm.core.designsystem.component.QRAlarmSwitch
import com.sweak.qralarm.core.designsystem.icon.QRAlarmIcons
import com.sweak.qralarm.core.designsystem.theme.QRAlarmTheme
import com.sweak.qralarm.core.designsystem.theme.space
import com.sweak.qralarm.features.add_edit_alarm.AddEditAlarmViewModel
import com.sweak.qralarm.features.add_edit_alarm.components.ChooseGentleWakeUpDurationBottomSheet
import com.sweak.qralarm.features.add_edit_alarm.components.ChooseTemporaryMuteDurationBottomSheet
import com.sweak.qralarm.features.add_edit_alarm.main_settings.AddEditAlarmFlowUserEvent.AdvancedAlarmScreenUserEvent
import com.sweak.qralarm.features.add_edit_alarm.main_settings.AddEditAlarmScreenState
import com.sweak.qralarm.features.add_edit_alarm.main_settings.getSecondsDurationString

@Composable
fun AdvancedAlarmSettingsScreen(
    addEditAlarmViewModel: AddEditAlarmViewModel,
    onCancelClicked: () -> Unit,
) {
    val addEditAlarmScreenState by addEditAlarmViewModel.state.collectAsStateWithLifecycle()

    AdvancedAlarmSettingsScreenContent(
        state = addEditAlarmScreenState,
        onEvent = { event ->
            when (event) {
                is AdvancedAlarmScreenUserEvent.OnCancelClicked -> onCancelClicked()
                else -> addEditAlarmViewModel.onEvent(event)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdvancedAlarmSettingsScreenContent(
    state: AddEditAlarmScreenState,
    onEvent: (AdvancedAlarmScreenUserEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.advanced_settings),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent(AdvancedAlarmScreenUserEvent.OnCancelClicked) }
                    ) {
                        Icon(
                            imageVector = QRAlarmIcons.BackArrow,
                            contentDescription =
                            stringResource(R.string.content_description_back_arrow_icon)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
        ) {
            QRAlarmCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.space.medium,
                        vertical = MaterialTheme.space.mediumLarge
                    )
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            onEvent(
                                AdvancedAlarmScreenUserEvent
                                    .ChooseGentleWakeUpDurationDialogVisible(isVisible = true)
                            )
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = MaterialTheme.space.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = MaterialTheme.space.smallMedium)
                        ) {
                            Text(
                                text = stringResource(R.string.gentle_wake_up),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(bottom = MaterialTheme.space.xSmall)
                            )

                            Text(
                                text = stringResource(R.string.gentle_wake_up_description),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = getSecondsDurationString(
                                    state.gentleWakeupDurationInSeconds
                                ),
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(end = MaterialTheme.space.small)
                            )

                            Icon(
                                imageVector = QRAlarmIcons.ForwardArrow,
                                contentDescription = stringResource(
                                    R.string.content_description_forward_arrow_icon
                                ),
                                modifier = Modifier.size(size = MaterialTheme.space.medium)
                            )
                        }
                    }
                }

                Separator()

                Box(
                    modifier = Modifier
                        .clickable {
                            onEvent(
                                AdvancedAlarmScreenUserEvent
                                    .ChooseTemporaryMuteDurationDialogVisible(isVisible = true)
                            )
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = MaterialTheme.space.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = MaterialTheme.space.smallMedium)
                        ) {
                            Text(
                                text = stringResource(R.string.temporary_mute),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(bottom = MaterialTheme.space.xSmall)
                            )

                            Text(
                                text = stringResource(R.string.temporary_mute_description),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = getSecondsDurationString(
                                    state.temporaryMuteDurationInSeconds
                                ),
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(end = MaterialTheme.space.small)
                            )

                            Icon(
                                imageVector = QRAlarmIcons.ForwardArrow,
                                contentDescription = stringResource(
                                    R.string.content_description_forward_arrow_icon
                                ),
                                modifier = Modifier.size(size = MaterialTheme.space.medium)
                            )
                        }
                    }
                }
            }

            if (state.isCodeEnabled) {
                QRAlarmCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.space.medium,
                            end = MaterialTheme.space.medium,
                            bottom = MaterialTheme.space.mediumLarge
                        )
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = MaterialTheme.space.medium)
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        end = MaterialTheme.space.smallMedium
                                    )
                            ) {
                                Text(
                                    text = stringResource(
                                        R.string.open_code_link
                                    ),
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .padding(
                                            bottom = MaterialTheme.space.xSmall
                                        )
                                )

                                Text(
                                    text = stringResource(
                                        R.string.open_code_link_description
                                    ),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            QRAlarmSwitch(
                                checked = state.isOpenCodeLinkEnabled,
                                onCheckedChange = {
                                    onEvent(
                                        AdvancedAlarmScreenUserEvent.OpenCodeLinkEnabledChanged(
                                            isEnabled = it
                                        )
                                    )
                                }
                            )
                        }
                    }

                    Separator()

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = MaterialTheme.space.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    end = MaterialTheme.space.smallMedium
                                )
                        ) {
                            Text(
                                text = stringResource(R.string._1_hour_lock),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(
                                        bottom = MaterialTheme.space.xSmall
                                    )
                            )

                            Text(
                                text = stringResource(
                                    R.string._1_hour_lock_description
                                ),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        QRAlarmSwitch(
                            checked = state.isOneHourLockEnabled,
                            onCheckedChange = {
                                onEvent(
                                    AdvancedAlarmScreenUserEvent.OneHourLockEnabledChanged(
                                        isEnabled = it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (state.isChooseGentleWakeUpDurationDialogVisible) {
        ChooseGentleWakeUpDurationBottomSheet(
            initialGentleWakeUpDurationInSeconds = state.gentleWakeupDurationInSeconds,
            availableGentleWakeUpDurationsInSeconds = state.availableGentleWakeUpDurationsInSeconds,
            onDismissRequest = { newGentleWakeUpDurationInSeconds ->
                onEvent(
                    AdvancedAlarmScreenUserEvent.GentleWakeUpDurationSelected(
                        newGentleWakeUpDurationInSeconds = newGentleWakeUpDurationInSeconds
                    )
                )
            }
        )
    }

    if (state.isChooseTemporaryMuteDurationDialogVisible) {
        ChooseTemporaryMuteDurationBottomSheet(
            initialTemporaryMuteDurationInSeconds = state.temporaryMuteDurationInSeconds,
            availableTemporaryMuteDurationsInSeconds =
            state.availableTemporaryMuteDurationsInSeconds,
            onDismissRequest = { newTemporaryMuteDurationInSeconds ->
                onEvent(
                    AdvancedAlarmScreenUserEvent.TemporaryMuteDurationSelected(
                        newTemporaryMuteDurationInSeconds = newTemporaryMuteDurationInSeconds
                    )
                )
            }
        )
    }
}

@Composable
private fun Separator() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = MaterialTheme.space.medium)
            .background(color = MaterialTheme.colorScheme.onSurface)
    )
}

@Preview
@Composable
private fun AdvancedAlarmSettingsScreenContentPreview() {
    QRAlarmTheme {
        AdvancedAlarmSettingsScreenContent(
            state = AddEditAlarmScreenState(),
            onEvent = {}
        )
    }
}