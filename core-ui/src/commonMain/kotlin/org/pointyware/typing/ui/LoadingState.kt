package org.pointyware.typing.ui

/**
 *
 */
sealed class LoadingState {
    data object Idle : LoadingState()
    data object Loading : LoadingState()
    data class Error(
        val message: String
    ): LoadingState()
}
