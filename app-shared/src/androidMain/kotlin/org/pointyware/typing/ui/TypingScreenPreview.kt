package org.pointyware.typing.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import org.pointyware.typing.data.TypingProgress
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.viewmodels.TimerUiState
import org.pointyware.typing.viewmodels.TypingUiState

/**
 *
 */
@LightDarkModePreview
@TestDevicePreviews
@Composable
fun TypingScreenPreview() {
    TypingTheme {
        Surface {
            TypingView(
                state = TypingUiState(
                    subjectSource = null,
                    subject = "Some subject",
                    progress = TypingProgress(
                        string = "some subject",
                        incorrect = listOf(0..0),
                        wpm = 80.0f,
                        accuracy = 0.9f
                    ),
                    loadingState = LoadingState.Idle,
                    timerState = TimerUiState.Running(timeRemaining = 15)
                ),
                onCodePoint = { true },
                onDelete = {},
                onReset = {}
            )
        }
    }
}
