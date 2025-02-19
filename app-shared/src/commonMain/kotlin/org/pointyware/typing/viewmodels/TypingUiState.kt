package org.pointyware.typing.viewmodels

import org.pointyware.typing.data.SubjectSource
import org.pointyware.typing.data.TypingProgress
import org.pointyware.typing.ui.LoadingState

/**
 *
 */
data class TypingUiState(
    val subjectSource: SubjectSource?,
    val subject: String,
    val progress: TypingProgress,
    val timerState: TimerUiState,
    val loadingState: LoadingState,
) {
    companion object {
        val Empty = TypingUiState(
            subjectSource = null,
            subject = "",
            progress = TypingProgress("", emptyList(), 0f, 0f),
            timerState = TimerUiState.Stopped,
            loadingState = LoadingState.Idle,
        )
    }
}
