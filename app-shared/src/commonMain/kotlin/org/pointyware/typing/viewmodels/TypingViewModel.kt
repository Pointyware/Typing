package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel
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
    fun onKeyStroke(codePoint: Int): Boolean
    fun onInputChange(input: String)
    fun onDelete()
}

/**
 *
 */
class TypingViewModelImpl(
    private val typingController: TypingController
) : ViewModel(), TypingViewModel {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override val state: StateFlow<TypingUiState>
        get() = combine(typingController.subject, typingController.progress) { subject, progress ->
            TypingUiState(
                subject = subject,
                progress = progress,
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

    override fun onKeyStroke(codePoint: Int): Boolean {
        if (typingController.isRunning.value) {
            typingController.consume(codePoint.toChar())
        } else {
            typingController.start()
        }
        val character = codePoint.toChar()
        return if (character.isDefined()) {
            typingController.consume(character)
            true
        } else {
            false
        }
    }

    override fun onInputChange(input: String) {
        typingController.start()
        typingController.setInput(input)
    }

    override fun onDelete() {
        typingController
    }
}
