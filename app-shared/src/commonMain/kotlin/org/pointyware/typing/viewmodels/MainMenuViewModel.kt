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
            println("Loading subjects")
            _state.update {
                it.copy(
                    loadingState = LoadingState.Loading
                )
            }
            loadSubjectsUseCase()
                .onSuccess { loadedSubjects ->
                    println("Loaded subjects: ${loadedSubjects.vocabList.size} vocab, ${loadedSubjects.storiesList.size} stories")
                    _state.update {
                        it.copy(
                            vocabList = loadedSubjects.vocabList,
                            storiesList = loadedSubjects.storiesList,
                            loadingState = LoadingState.Idle
                        )
                    }
                }
                .onFailure {  error ->
                    println("Failed to load subjects: ${error.message}")
                    error.printStackTrace()
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.Error(error.message ?: "Unknown error")
                        )
                    }
                }
            loadingJob = null
            println("Finished loading subjects")
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
