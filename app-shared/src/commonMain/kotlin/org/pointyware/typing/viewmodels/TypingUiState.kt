package org.pointyware.typing.viewmodels

import org.pointyware.typing.ui.LoadingState

/**
 *
 */
data class TypingUiState(
    val subject: String = "",
    val progress: String = "",
    val wpm: Float = 0.0f,
    val timerState: TimerUiState,
    val loadingState: LoadingState = LoadingState.Idle,
) {
    companion object {
        val Empty = TypingUiState(
            subject = "",
            progress = "",
            wpm = 0.0f,
            timerState = TimerUiState.Stopped,
            loadingState = LoadingState.Idle,
        )
    }
}
