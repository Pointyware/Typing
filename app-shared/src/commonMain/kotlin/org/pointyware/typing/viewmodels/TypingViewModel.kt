package org.pointyware.typing.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface TypingViewModel {
    val state: StateFlow<TypingUiState>

    fun onReset()
    fun onKeyStroke(key: Char)
}

/**
 *
 */
class TypingViewModelImpl(

): TypingViewModel {

    private val mutableState = MutableStateFlow(TypingUiState.Empty)
    override val state: StateFlow<TypingUiState>
        get() = mutableState.asStateFlow()

    override fun onReset() {

    }

    override fun onKeyStroke(key: Char) {

    }
}
