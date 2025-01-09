package org.pointyware.typing.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.viewmodels.TimerUiState

/**
 */
@LightDarkModePreview
@Composable
fun TimerPreview(
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
