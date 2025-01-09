package org.pointyware.typing.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.viewmodels.TimerUiState

/**
 */
@LightDarkModePreview
@Composable
fun StoppedTimerPreview(
    modifier: Modifier = Modifier,
) {
    Surface {
        TypingTheme {
            Timer(
                state = TimerUiState.Stopped
            )
        }
    }
}
/**
 */
@LightDarkModePreview
@Composable
fun HiddenTimerPreview(
    modifier: Modifier = Modifier,
) {
    Surface {
        TypingTheme {
            Timer(
                state = TimerUiState.Hidden
            )
        }
    }
}

/**
 */
@LightDarkModePreview
@Composable
fun RunningTimerPreview(
    modifier: Modifier = Modifier,
) {
    Surface {
        TypingTheme {
            Timer(
                state = TimerUiState.Running(
                    timeRemaining = 1000
                )
            )
        }
    }
}
