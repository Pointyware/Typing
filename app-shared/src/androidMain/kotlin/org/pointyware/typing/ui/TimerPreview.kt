package org.pointyware.typing.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.typing.viewmodels.TimerUiState

/**
 */
@Preview
@Composable
fun TimerPreview(
    modifier: Modifier = Modifier,
) {
    Timer(
        state = TimerUiState.Running(
            timeRemaining = 1000
        )
    )
}
