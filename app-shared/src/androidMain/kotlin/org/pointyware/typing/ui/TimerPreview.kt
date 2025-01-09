package org.pointyware.typing.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.viewmodels.TimerUiState

class TimerStateProvider: PreviewParameterProvider<TimerUiState> {
    override val values = sequenceOf(
        TimerUiState.Hidden,
        TimerUiState.Stopped,
        TimerUiState.Running(
            timeRemaining = 1000
        )
    )
}

/**
 */
@LightDarkModePreview
@Composable
fun StoppedTimerPreview(
    @PreviewParameter(TimerStateProvider::class) state: TimerUiState
) {
    Surface {
        TypingTheme {
            Timer(
                state = state
            )
        }
    }
}
