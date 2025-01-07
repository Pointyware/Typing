package org.pointyware.typing.viewmodels

import org.pointyware.typing.ui.LoadingState

/**
 *
 */
data class TypingUiState(
    val subject: String = "",
    val loadingState: LoadingState = LoadingState.Idle,
) {
    companion object {
        val Empty = TypingUiState()
    }
}
