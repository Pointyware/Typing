package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.pointyware.typing.data.SubjectSource
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.ui.LoadingState

interface TypingViewModel {
    val state: StateFlow<TypingUiState>

    fun setSubjectSource(source: SubjectSource)
    fun onReset()
    fun onKeyStroke(codePoint: Int): Boolean
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
                subjectSource = null,
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

    private var loadingJob: Job? = null
    override fun setSubjectSource(source: SubjectSource) {
        loadingJob?.cancel("Loading job restarted")
        loadingJob = viewModelScope.launch {
            typingController.setSubjectSource(source)
        }
    }

    override fun onReset() {
        typingController.reset()
    }

    override fun onKeyStroke(codePoint: Int): Boolean {
        if (!typingController.isRunning.value) {
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

    override fun onDelete() {
        typingController.delete()
    }
}
