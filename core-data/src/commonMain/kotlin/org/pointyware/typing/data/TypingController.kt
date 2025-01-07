package org.pointyware.typing.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 *
 */
interface TypingController {

    val subject: StateFlow<String>
    val progress: StateFlow<TypingProgress>
    val timeRemaining: StateFlow<Float>
    val wpm: StateFlow<Float>

    /**
     * Reset the controller state.
     */
    fun reset()

    /**
     * Process a key stroke.
     */
    fun consume(key: Char)
}

class TypingControllerImpl(): TypingController {

    private val mutableSubject = MutableStateFlow("")
    override val subject: StateFlow<String>
        get() = mutableSubject.asStateFlow()

    private val mutableProgress = MutableStateFlow(TypingProgress("", emptyList()))
    override val progress: StateFlow<TypingProgress>
        get() = mutableProgress.asStateFlow()

    private val mutableTimeRemaining = MutableStateFlow(0f)
    override val timeRemaining: StateFlow<Float>
        get() = mutableTimeRemaining.asStateFlow()

    private val mutableWpm = MutableStateFlow(0f)
    override val wpm: StateFlow<Float>
        get() = mutableWpm.asStateFlow()

    override fun reset() {

    }

    override fun consume(key: Char) {
        TODO("Not yet implemented")
    }
}

data class TypingProgress(
    val string: String,
    val incorrect: List<IntRange>
)
