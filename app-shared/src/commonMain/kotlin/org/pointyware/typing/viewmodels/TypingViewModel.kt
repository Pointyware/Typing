package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.pointyware.typing.data.SubjectSourceRegistry
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.ui.LoadingState

interface TypingViewModel {
    val state: StateFlow<TypingUiState>

    fun setSubjectSource(id: Int)
    fun onReset()
    fun onKeyStroke(codePoint: Int): Boolean
    fun onDelete()
}

/**
 *
 */
class TypingViewModelImpl(
    private val typingController: TypingController,
    private val subjectSourceRegistry: SubjectSourceRegistry
) : ViewModel(), TypingViewModel {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private val mutableLoadingState = MutableStateFlow<LoadingState>(LoadingState.Idle)

    override val state: StateFlow<TypingUiState>
        get() = combine(
            typingController.subject,
            typingController.progress,
            mutableLoadingState
        ) { subject, progress, loadingState ->
            TypingUiState(
                subjectSource = null,
                subject = subject,
                progress = progress,
                timerState = TimerUiState.Hidden,
                loadingState = loadingState,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = TypingUiState.Empty
        )

    private var loadingJob: Job? = null
    override fun setSubjectSource(id: Int) {
        loadingJob?.cancel("Loading job restarted")
        loadingJob = viewModelScope.launch {
            mutableLoadingState.value = LoadingState.Loading

            subjectSourceRegistry.get(id)?.let {
                typingController.setSubjectSource(it)
            } ?: run {
                mutableLoadingState.value = LoadingState.Error("Subject source not found")
                return@launch
            }

            mutableLoadingState.value = LoadingState.Idle
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
