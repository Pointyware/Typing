package org.pointyware.typing.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.ui.LoadingState

interface TypingViewModel {
    val state: StateFlow<TypingUiState>

    fun onReset()
    fun onKeyStroke(key: Char)
    fun onInputChange(input: String)
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
                wpm = progress.wpm,
                timerState = TimerUiState.Hidden,
                loadingState = LoadingState.Idle,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = TypingUiState.Empty
        )

    override fun onReset() {
        typingController.reset()
    }

    override fun onKeyStroke(key: Char) {
        typingController.start()
        typingController.consume(key)
    }

    override fun onInputChange(input: String) {
        typingController.start()
        typingController.setInput(input)
    }
}
