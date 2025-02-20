package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Represents a UI state for the main menu screen.
 */
interface MainMenuViewModel {
    val state: StateFlow<MainMenuUiState>

    fun onSelectWords(index: Int)
    fun onSelectParagraphs(index: Int)
}

/**
 *
 */
class MainMenuViewModelImpl(

): ViewModel(), MainMenuViewModel {

    private val _state = MutableStateFlow(MainMenuUiState.Empty)
    override val state: StateFlow<MainMenuUiState>
        get() = _state.asStateFlow()

    override fun onSelectWords(index: Int) {
        _state.update {
            it.copy(
                selectedVocab = it.vocabList.getOrNull(index),
            )
        }
    }

    override fun onSelectParagraphs(index: Int) {
        _state.update {
            it.copy(
                selectedParagraphs = it.storiesList.getOrNull(index),
            )
        }
    }
}
