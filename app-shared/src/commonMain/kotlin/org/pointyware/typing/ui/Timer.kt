package org.pointyware.typing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pointyware.typing.typing.Res
import org.pointyware.typing.typing.label_timer_hidden
import org.pointyware.typing.typing.label_timer_stopped
import org.pointyware.typing.viewmodels.TimerUiState

/**
 * Displays the time remaining if enabled; otherwise, displays a placeholder.
 */
@Composable
fun Timer(
    state: TimerUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.Check,
            contentDescription = "Timer"
        )
        when (state) {
            TimerUiState.Hidden -> {
                // no op
                Text(text = stringResource(Res.string.label_timer_hidden))
            }

            is TimerUiState.Running -> {
                Text(state.timeRemaining.toString())
            }

            TimerUiState.Stopped -> {
                Text(text = stringResource(Res.string.label_timer_stopped))
            }
        }
    }
}
