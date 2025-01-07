package org.pointyware.typing.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.ui.LoadingState

interface TypingViewModel {
    val state: StateFlow<TypingUiState>

    fun onReset()
    fun onKeyStroke(key: Char)
}

/**
 *
 */
class TypingViewModelImpl(
    private val typingController: TypingController
): TypingViewModel {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override val state: StateFlow<TypingUiState>
        get() = combine(typingController.subject, typingController.progress) { subject, progress ->
            TypingUiState(
                subject = subject,
                progress = progress,
                wpm = 0f,
                timerState = TimerUiState.Hidden,
                loadingState = LoadingState.Idle,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = TypingUiState.Empty
        )

    override fun onReset() {
        typingController.reset()
    }

    override fun onKeyStroke(key: Char) {
        typingController.consume(key)
    }
}
