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

class TypingControllerImpl(
    private val subjectProvider: SubjectProvider
): TypingController {

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
        mutableSubject.update { subjectProvider.nextSubject() }
        mutableProgress.update { TypingProgress("", emptyList()) }
        mutableTimeRemaining.update { 0f }
        mutableWpm.update { 0f }
    }

    override fun consume(key: Char) {
        currentInput += key
        setInput(currentInput) // FIXME: this is inefficient
    }

    override fun setInput(input: String) {
        mutableProgress.update {
            TypingProgress(
                input,
                findMismatchedRanges(subject.value, input)
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
                ranges.add(start until position)
            } else {
                position++
            }
        }
        if (input.length > subject.length) {
            ranges.add(subject.length until input.length)
        }
        return ranges
    }
}
