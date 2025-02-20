package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.typing.interactors.LoadSubjectsUseCase
import org.pointyware.typing.ui.LoadingState

/**
 * Represents a UI state for the main menu screen.
 */
interface MainMenuViewModel {
    val state: StateFlow<MainMenuUiState>

    fun load()
    fun onSelectWords(index: Int)
    fun onSelectParagraphs(index: Int)
}

/**
 *
 */
class MainMenuViewModelImpl(
    private val loadSubjectsUseCase: LoadSubjectsUseCase
): ViewModel(), MainMenuViewModel {

    private val _state = MutableStateFlow(MainMenuUiState.Empty)
    override val state: StateFlow<MainMenuUiState>
        get() = _state.asStateFlow()

    private var loadingJob: Job? = null
    override fun load() {
        loadingJob?.cancel("Loading job restarted")
        loadingJob = viewModelScope.launch {
            loadSubjectsUseCase()
                .onSuccess { loadedSubjects ->
                    _state.update {
                        it.copy(
                            vocabList = loadedSubjects.vocabList,
                            storiesList = loadedSubjects.storiesList
                        )
                    }
                }
                .onFailure {  error ->
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.Error(error.message ?: "Unknown error")
                        )
                    }
                }
            loadingJob = null
        }
    }

    override fun onSelectWords(index: Int) {
        _state.update {
            it.copy(
                selectedVocab = index,
            )
        }
    }

    override fun onSelectParagraphs(index: Int) {
        _state.update {
            it.copy(
                selectedParagraphs = index,
            )
        }
    }
}
