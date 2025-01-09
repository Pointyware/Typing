package org.pointyware.typing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.typing.viewmodels.TimerUiState

/**
 *
 */
@Composable
fun Timer(
    state: TimerUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Image(
            imageVector = Icons.Default.Check,
            contentDescription = "Timer"
        )
        when (state) {
            TimerUiState.Hidden -> {
                // no op
                Text("Time Hidden")
            }

            is TimerUiState.Running -> {
                Text(state.timeRemaining.toString())
            }

            TimerUiState.Stopped -> {
                Text("--")
            }
        }
    }
}
