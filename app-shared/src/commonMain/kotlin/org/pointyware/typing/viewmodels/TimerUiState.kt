package org.pointyware.typing.viewmodels

/**
 *
 */
sealed interface TimerUiState {
    data object Stopped: TimerUiState
    data class Running(
        val timeRemaining: Int
    ): TimerUiState
    data object Hidden: TimerUiState
}
