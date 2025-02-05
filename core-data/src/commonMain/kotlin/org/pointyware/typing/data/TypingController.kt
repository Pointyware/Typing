package org.pointyware.typing.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

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
     * Start tracking typing progress. If tracking is already started, does nothing. [reset] must be
     * called first to mark a new start time.
     */
    fun start()

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

    private val mutableProgress = MutableStateFlow(TypingProgress("", emptyList(), 0f, 0f))
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
        timeStarted = null
        mutableSubject.update { subjectProvider.nextSubject() }
        mutableProgress.update { TypingProgress("", emptyList(), 0f, 1f) }
        mutableTimeRemaining.update { 0f }
        mutableWpm.update { 0f }
    }

    private var timeStarted: Instant? = null
    override fun start() {
        if (timeStarted == null) {
            timeStarted = Clock.System.now()
        }
    }

    override fun consume(key: Char) {
        currentInput += key
        setInput(currentInput) // FIXME: this is inefficient
    }

    override fun setInput(input: String) {
        currentInput = input
        val wpm = timeStarted?.let {
            val elapsed = Clock.System.now() - it
            val minutes = elapsed.inWholeMilliseconds / 60.0e3f
            val words = input.length / 5f
            words / minutes
        } ?: 0f
        val mismatchedRanges = findMismatchedRangesForSubject(subject.value, input)
        val missedCharacters = mismatchedRanges.sumOf { it.count() }
        val accuracy = 1f - missedCharacters / subject.value.length.toFloat()
        mutableProgress.update {
            TypingProgress(
                input,
                mismatchedRanges,
                wpm,
                accuracy
            )
        }
    }

    private fun findMismatchedRangesForSubject(subject: String, input: String): MutableList<IntRange> {
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
        return ranges
    }

    private fun findMismatchedRangesForInput(subject: String, input: String): MutableList<IntRange> {
        val mismatchedRanges = findMismatchedRangesForSubject(subject, input)
        if (input.length > subject.length) {
            mismatchedRanges.add(subject.length until input.length)
        }
        return mismatchedRanges
    }
}
