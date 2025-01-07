package org.pointyware.typing.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    /**
     * Atomically set the input string.
     */
    fun setInput(input: String)
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

    private var currentInput = ""
    override fun reset() {
        currentInput = ""
        mutableSubject.update { "" }
        mutableProgress.update { TypingProgress("", emptyList()) }
        mutableTimeRemaining.update { 0f }
        mutableWpm.update { 0f }
    }

    override fun consume(key: Char) {
        currentInput += key
        setInput(currentInput) // FIXME: this is inefficient
    }

    override fun setInput(input: String) {
        val subject = subject.value
        mutableProgress.update {
            TypingProgress(
                input,
                findMismatchedRanges(subject, input)
            )
        }
    }

    private fun findMismatchedRanges(subject: String, input: String): List<IntRange> {
        val ranges = mutableListOf<IntRange>()
        var position = 0
        val maxLength = minOf(subject.length, input.length)
        while (position < maxLength) {
            if (subject[position] != input[position]) {
                val start = position
                while (position < maxLength && subject[position] != input[position]) {
                    position++
                }
                if (position < maxLength) { // if we're not at the end of the string
                    ranges.add(start until position)
                } else { // reached end of input or subject
                    ranges.add(start until input.length)
                }
            } else {
                position++
            }
        }
        return ranges
    }
}

data class TypingProgress(
    val string: String,
    val incorrect: List<IntRange>
)
